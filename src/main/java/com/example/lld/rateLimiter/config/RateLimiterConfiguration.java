package com.example.lld.rateLimiter.config;

import com.example.lld.rateLimiter.enums.RateLimitAlgorithm;

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

        configs.put("/api/login", new EndpointRateLimitConfig(
                "/api/login",
                RateLimitAlgorithm.TokenBucket,
                5,
                1.0,
                0,
                0));

        configs.put("/api/search", new EndpointRateLimitConfig(
                "/api/search",
                RateLimitAlgorithm.SlidingWindowLog,
                0,
                0,
                10,
                60_000));

        configs.put("/api/profile", new EndpointRateLimitConfig(
                "/api/profile",
                RateLimitAlgorithm.TokenBucket,
                3,
                0.5,
                0,
                0));

        EndpointRateLimitConfig defaultConfig = new EndpointRateLimitConfig(
                "default",
                RateLimitAlgorithm.TokenBucket,
                20,
                1.0,
                0,
                0);

        return new RateLimiterConfiguration(configs, defaultConfig);
    }

    public Map<String, EndpointRateLimitConfig> getEndpointConfigs() {
        return endpointConfigs;
    }

    public EndpointRateLimitConfig getDefaultConfig() {
        return defaultConfig;
    }
}
