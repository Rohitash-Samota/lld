package com.example.lld.ratelimiter.services;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.lld.ratelimiter.config.EndpointRateLimitConfig;
import com.example.lld.ratelimiter.config.RateLimitProperties;
import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.factory.RateLimiterFactory;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

@Service
public class RedisRateLimiterService {

    private final Map<String, EndpointRateLimitConfig> endpointConfigs;
    private final Map<String, RateLimiter> endpointLimiters;

    public RedisRateLimiterService(RateLimitProperties properties, StringRedisTemplate redisTemplate) {
        endpointConfigs = properties.endpointConfigs();
        endpointLimiters = Collections.unmodifiableMap(endpointConfigs.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> RateLimiterFactory.createRedisLimiter(entry.getValue(), redisTemplate))));
    }

    public boolean isConfigured(String endpoint) {
        return endpointLimiters.containsKey(endpoint);
    }

    public int getLimit(String endpoint) {
        EndpointRateLimitConfig config = endpointConfigs.get(endpoint);
        if (config == null) {
            throw new IllegalArgumentException("No rate limit configured for endpoint: " + endpoint);
        }
        return config.limit();
    }

    public ResponseRateLimiter allowRequest(String clientId, String endpoint) {
        RateLimiter limiter = endpointLimiters.get(endpoint);
        if (limiter == null) {
            throw new IllegalArgumentException("No rate limit configured for endpoint: " + endpoint);
        }
        return limiter.allowRequest(clientId);
    }
}