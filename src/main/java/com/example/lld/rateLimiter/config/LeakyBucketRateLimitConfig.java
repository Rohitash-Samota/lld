package com.example.lld.ratelimiter.config;

import com.example.lld.ratelimiter.enums.RateLimitAlgorithm;

public record LeakyBucketRateLimitConfig(String endpoint, int capacity, double leakRatePerSecond)
        implements EndpointRateLimitConfig {

    public LeakyBucketRateLimitConfig {
        EndpointRateLimitConfigValidation.validateEndpoint(endpoint);
        if (capacity <= 0 || !Double.isFinite(leakRatePerSecond) || leakRatePerSecond <= 0) {
            throw new IllegalArgumentException("Leaky bucket requires positive capacity and leak rate");
        }
    }

    @Override
    public RateLimitAlgorithm algorithm() {
        return RateLimitAlgorithm.LEAKY_BUCKET;
    }

    @Override
    public int limit() {
        return capacity;
    }
}