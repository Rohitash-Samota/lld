package com.example.lld.ratelimiter.implementation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import com.example.lld.ratelimiter.config.EndpointRateLimitConfig;
import com.example.lld.ratelimiter.config.FixedWindowCounterRateLimitConfig;
import com.example.lld.ratelimiter.config.LeakyBucketRateLimitConfig;
import com.example.lld.ratelimiter.config.SlidingWindowCounterRateLimitConfig;
import com.example.lld.ratelimiter.config.SlidingWindowLogRateLimitConfig;
import com.example.lld.ratelimiter.config.TokenBucketRateLimitConfig;
import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class RedisRateLimiter implements RateLimiter {

    private static final DefaultRedisScript<String> TOKEN_BUCKET_SCRIPT = script("""
            local time = redis.call('TIME')
            local now = tonumber(time[1]) * 1000 + math.floor(tonumber(time[2]) / 1000)
            local capacity = tonumber(ARGV[1])
            local rate = tonumber(ARGV[2])
            local tokens = tonumber(redis.call('HGET', KEYS[1], 'tokens')) or capacity
            local last = tonumber(redis.call('HGET', KEYS[1], 'updated')) or now
            tokens = math.min(capacity, tokens + math.max(0, now - last) * rate / 1000)
            local allowed = 0
            local retry = 0
            if tokens >= 1 then
                tokens = tokens - 1
                allowed = 1
            else
                retry = math.ceil((1 - tokens) * 1000 / rate)
            end
            redis.call('HSET', KEYS[1], 'tokens', tostring(tokens), 'updated', tostring(now))
            redis.call('PEXPIRE', KEYS[1], math.max(1000, math.ceil(capacity * 2000 / rate)))
            return tostring(allowed) .. '|' .. tostring(math.floor(tokens)) .. '|' .. tostring(retry)
            """);

    private static final DefaultRedisScript<String> FIXED_WINDOW_SCRIPT = script("""
            local time = redis.call('TIME')
            local now = tonumber(time[1]) * 1000 + math.floor(tonumber(time[2]) / 1000)
            local window = tonumber(ARGV[2])
            local bucket = KEYS[1] .. ':fw:' .. tostring(math.floor(now / window))
            local count = redis.call('INCR', bucket)
            if count == 1 then redis.call('PEXPIRE', bucket, window + 1000) end
            local allowed = count <= tonumber(ARGV[1]) and 1 or 0
            local remaining = math.max(0, tonumber(ARGV[1]) - count)
            local retry = allowed == 1 and 0 or window - (now % window)
            return tostring(allowed) .. '|' .. tostring(remaining) .. '|' .. tostring(retry)
            """);

    private static final DefaultRedisScript<String> SLIDING_WINDOW_LOG_SCRIPT = script("""
            local time = redis.call('TIME')
            local now = tonumber(time[1]) * 1000 + math.floor(tonumber(time[2]) / 1000)
            local window = tonumber(ARGV[2])
            local limit = tonumber(ARGV[1])
            redis.call('ZREMRANGEBYSCORE', KEYS[1], '-inf', now - window)
            local count = redis.call('ZCARD', KEYS[1])
            if count >= limit then
                local oldest = redis.call('ZRANGE', KEYS[1], 0, 0, 'WITHSCORES')
                local retry = math.max(1, tonumber(oldest[2]) + window - now)
                return '0|0|' .. tostring(retry)
            end
            redis.call('ZADD', KEYS[1], now, ARGV[3])
            redis.call('PEXPIRE', KEYS[1], window + 1000)
            return '1|' .. tostring(limit - count - 1) .. '|0'
            """);

    private static final DefaultRedisScript<String> SLIDING_WINDOW_COUNTER_SCRIPT = script("""
            local time = redis.call('TIME')
            local now = tonumber(time[1]) * 1000 + math.floor(tonumber(time[2]) / 1000)
            local window = tonumber(ARGV[2])
            local limit = tonumber(ARGV[1])
            local index = math.floor(now / window)
            local currentKey = KEYS[1] .. ':swc:' .. tostring(index)
            local previousKey = KEYS[1] .. ':swc:' .. tostring(index - 1)
            local current = tonumber(redis.call('GET', currentKey)) or 0
            local previous = tonumber(redis.call('GET', previousKey)) or 0
            local weight = 1 - (now % window) / window
            local estimate = current + previous * weight
            if estimate >= limit then
                return '0|0|' .. tostring(math.max(1, window - (now % window)))
            end
            current = redis.call('INCR', currentKey)
            redis.call('PEXPIRE', currentKey, window * 2 + 1000)
            local remaining = math.max(0, limit - math.ceil(estimate + 1))
            return '1|' .. tostring(remaining) .. '|0'
            """);

    private static final DefaultRedisScript<String> LEAKY_BUCKET_SCRIPT = script("""
            local time = redis.call('TIME')
            local now = tonumber(time[1]) * 1000 + math.floor(tonumber(time[2]) / 1000)
            local capacity = tonumber(ARGV[1])
            local rate = tonumber(ARGV[2])
            local queued = tonumber(redis.call('HGET', KEYS[1], 'queued')) or 0
            local last = tonumber(redis.call('HGET', KEYS[1], 'updated')) or now
            queued = math.max(0, queued - math.max(0, now - last) * rate / 1000)
            redis.call('HSET', KEYS[1], 'queued', tostring(queued), 'updated', tostring(now))
            if queued + 1 > capacity then
                local retry = math.ceil((queued + 1 - capacity) * 1000 / rate)
                return '0|0|' .. tostring(retry)
            end
            queued = queued + 1
            redis.call('HSET', KEYS[1], 'queued', tostring(queued))
            redis.call('PEXPIRE', KEYS[1], math.max(1000, math.ceil(capacity * 2000 / rate)))
            return '1|' .. tostring(math.max(0, math.floor(capacity - queued))) .. '|0'
            """);

    private final StringRedisTemplate redisTemplate;
    private final EndpointRateLimitConfig config;

    public RedisRateLimiter(StringRedisTemplate redisTemplate, EndpointRateLimitConfig config) {
        this.redisTemplate = redisTemplate;
        this.config = config;
    }

    @Override
    public ResponseRateLimiter allowRequest(String clientId) {
        if (clientId == null || clientId.isBlank()) {
            return new ResponseRateLimiter(false, 0, null, "Missing client identifier");
        }

        String clientKey = createRedisKey(config.endpoint(), clientId);
        String result;
        if (config instanceof TokenBucketRateLimitConfig bucket) {
            result = redisTemplate.execute(TOKEN_BUCKET_SCRIPT, List.of(clientKey + ":token"),
                Integer.toString(bucket.capacity()), Double.toString(bucket.refillRatePerSecond()));
        } else if (config instanceof FixedWindowCounterRateLimitConfig window) {
            result = redisTemplate.execute(FIXED_WINDOW_SCRIPT, List.of(clientKey),
                Integer.toString(window.maxRequests()), Long.toString(window.windowSizeMs()));
        } else if (config instanceof SlidingWindowLogRateLimitConfig window) {
            result = redisTemplate.execute(SLIDING_WINDOW_LOG_SCRIPT, List.of(clientKey + ":log"),
                Integer.toString(window.maxRequests()), Long.toString(window.windowSizeMs()),
                UUID.randomUUID().toString());
        } else if (config instanceof SlidingWindowCounterRateLimitConfig window) {
            result = redisTemplate.execute(SLIDING_WINDOW_COUNTER_SCRIPT, List.of(clientKey),
                Integer.toString(window.maxRequests()), Long.toString(window.windowSizeMs()));
        } else if (config instanceof LeakyBucketRateLimitConfig bucket) {
            result = redisTemplate.execute(LEAKY_BUCKET_SCRIPT, List.of(clientKey + ":leaky"),
                Integer.toString(bucket.capacity()), Double.toString(bucket.leakRatePerSecond()));
        } else {
            throw new IllegalArgumentException("Unsupported endpoint rate limit config: " + config.getClass());
        }

        if (result == null) {
            throw new IllegalStateException("Redis returned no rate limit result");
        }
        String[] values = result.split("\\|");
        if (values.length != 3) {
            throw new IllegalStateException("Unexpected Redis rate limit result");
        }
        boolean allowed = "1".equals(values[0]);
        Long retryAfterMs = Long.parseLong(values[2]);
        return new ResponseRateLimiter(allowed, Integer.parseInt(values[1]), retryAfterMs,
                allowed ? "Request Allowed" : "Rate limit exceeded");
    }

    private static String createRedisKey(String endpoint, String clientId) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256")
                    .digest((endpoint + '\0' + clientId).getBytes(StandardCharsets.UTF_8));
            return "rate-limit:{" + HexFormat.of().formatHex(digest) + "}";
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is unavailable", exception);
        }
    }

    private static DefaultRedisScript<String> script(String source) {
        return new DefaultRedisScript<>(source, String.class);
    }
}