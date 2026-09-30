package com.example.lld.ratelimiter.config;

import com.example.lld.ratelimiter.enums.RateLimitAlgorithm;

public record SlidingWindowCounterRateLimitConfig(String endpoint, int maxRequests, long windowSizeMs)
        implements EndpointRateLimitConfig {

    public SlidingWindowCounterRateLimitConfig {
        EndpointRateLimitConfigValidation.validateEndpoint(endpoint);
        EndpointRateLimitConfigValidation.validateWindow(maxRequests, windowSizeMs);
    }

    @Override
    public RateLimitAlgorithm algorithm() {
        return RateLimitAlgorithm.SLIDING_WINDOW_COUNTER;
    }

    @Override
    public int limit() {
        return maxRequests;
    }
}