package com.example.lld.ratelimiter.factory;

import org.springframework.data.redis.core.StringRedisTemplate;

import com.example.lld.ratelimiter.config.EndpointRateLimitConfig;
import com.example.lld.ratelimiter.config.FixedWindowCounterRateLimitConfig;
import com.example.lld.ratelimiter.config.LeakyBucketRateLimitConfig;
import com.example.lld.ratelimiter.config.SlidingWindowCounterRateLimitConfig;
import com.example.lld.ratelimiter.config.SlidingWindowLogRateLimitConfig;
import com.example.lld.ratelimiter.config.TokenBucketRateLimitConfig;
import com.example.lld.ratelimiter.implementation.FixedWindowCounterRateLimiter;
import com.example.lld.ratelimiter.implementation.LeakyBucketRateLimiter;
import com.example.lld.ratelimiter.implementation.RedisRateLimiter;
import com.example.lld.ratelimiter.implementation.SlidingWindowCounterRateLimiter;
import com.example.lld.ratelimiter.implementation.SlidingWindowLogRateLimiter;
import com.example.lld.ratelimiter.implementation.TokenBucketRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class RateLimiterFactory {

    private RateLimiterFactory() {
    }

    public static RateLimiter createLimiter(EndpointRateLimitConfig config) {
        if (config instanceof TokenBucketRateLimitConfig bucket) {
            return new TokenBucketRateLimiter(bucket.capacity(), bucket.refillRatePerSecond());
        }
        if (config instanceof FixedWindowCounterRateLimitConfig window) {
            return new FixedWindowCounterRateLimiter(window.maxRequests(), window.windowSizeMs());
        }
        if (config instanceof SlidingWindowCounterRateLimitConfig window) {
            return new SlidingWindowCounterRateLimiter(window.maxRequests(), window.windowSizeMs());
        }
        if (config instanceof SlidingWindowLogRateLimitConfig window) {
            return new SlidingWindowLogRateLimiter(window.maxRequests(), window.windowSizeMs());
        }
        if (config instanceof LeakyBucketRateLimitConfig bucket) {
            return new LeakyBucketRateLimiter(bucket.capacity(), bucket.leakRatePerSecond());
        }
        throw new IllegalArgumentException("Unsupported endpoint rate limit config: " + config.getClass());
    }

    public static RateLimiter createRedisLimiter(EndpointRateLimitConfig config, StringRedisTemplate redisTemplate) {
        return new RedisRateLimiter(redisTemplate, config);
    }
}
