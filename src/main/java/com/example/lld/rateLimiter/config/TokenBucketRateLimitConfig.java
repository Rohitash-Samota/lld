package com.example.lld.ratelimiter.config;

import com.example.lld.ratelimiter.enums.RateLimitAlgorithm;

public record TokenBucketRateLimitConfig(String endpoint, int capacity, double refillRatePerSecond)
        implements EndpointRateLimitConfig {

    public TokenBucketRateLimitConfig {
        EndpointRateLimitConfigValidation.validateEndpoint(endpoint);
        if (capacity <= 0 || !Double.isFinite(refillRatePerSecond) || refillRatePerSecond <= 0) {
            throw new IllegalArgumentException("Token bucket requires positive capacity and refill rate");
        }
    }

    @Override
    public RateLimitAlgorithm algorithm() {
        return RateLimitAlgorithm.TOKEN_BUCKET;
    }

    @Override
    public int limit() {
        return capacity;
    }
}