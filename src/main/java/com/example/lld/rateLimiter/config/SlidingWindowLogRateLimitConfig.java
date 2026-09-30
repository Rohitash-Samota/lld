package com.example.lld.ratelimiter.config;

import com.example.lld.ratelimiter.enums.RateLimitAlgorithm;

public record SlidingWindowLogRateLimitConfig(String endpoint, int maxRequests, long windowSizeMs)
        implements EndpointRateLimitConfig {

    public SlidingWindowLogRateLimitConfig {
        EndpointRateLimitConfigValidation.validateEndpoint(endpoint);
        EndpointRateLimitConfigValidation.validateWindow(maxRequests, windowSizeMs);
    }

    @Override
    public RateLimitAlgorithm algorithm() {
        return RateLimitAlgorithm.SLIDING_WINDOW_LOG;
    }

    @Override
    public int limit() {
        return maxRequests;
    }
}