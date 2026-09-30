package com.example.lld.ratelimiter.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RateLimiterConfiguration {

    private final Map<String, EndpointRateLimitConfig> endpointConfigs;
    private final EndpointRateLimitConfig defaultConfig;

    private RateLimiterConfiguration(Map<String, EndpointRateLimitConfig> endpointConfigs,
            EndpointRateLimitConfig defaultConfig) {
        this.endpointConfigs = Collections.unmodifiableMap(endpointConfigs);
        this.defaultConfig = defaultConfig;
    }

    public static RateLimiterConfiguration load() {
        Map<String, EndpointRateLimitConfig> configs = new HashMap<>();

        configs.put("/api/login", new TokenBucketRateLimitConfig("/api/login", 5, 1.0));
        configs.put("/api/search", new SlidingWindowLogRateLimitConfig("/api/search", 10, 60_000));
        configs.put("/api/profile", new SlidingWindowCounterRateLimitConfig("/api/profile", 5, 60_000));
        configs.put("/api/fixed", new FixedWindowCounterRateLimitConfig("/api/fixed", 3, 60_000));
        configs.put("/api/export", new LeakyBucketRateLimitConfig("/api/export", 3, 1.0));

        EndpointRateLimitConfig defaultConfig = new TokenBucketRateLimitConfig("/default", 20, 1.0);
        return new RateLimiterConfiguration(configs, defaultConfig);
    }

    public Map<String, EndpointRateLimitConfig> getEndpointConfigs() {
        return endpointConfigs;
    }

    public EndpointRateLimitConfig getDefaultConfig() {
        return defaultConfig;
    }
}
