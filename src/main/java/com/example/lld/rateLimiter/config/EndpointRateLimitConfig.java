package com.example.lld.ratelimiter.config;

import com.example.lld.ratelimiter.enums.RateLimitAlgorithm;

public sealed interface EndpointRateLimitConfig
        permits TokenBucketRateLimitConfig,
                FixedWindowCounterRateLimitConfig,
                SlidingWindowCounterRateLimitConfig,
                SlidingWindowLogRateLimitConfig,
                LeakyBucketRateLimitConfig {

    String endpoint();

    RateLimitAlgorithm algorithm();

    int limit();
}

final class EndpointRateLimitConfigValidation {

    private EndpointRateLimitConfigValidation() {
    }

    static void validateEndpoint(String endpoint) {
        if (endpoint == null || endpoint.isBlank() || !endpoint.startsWith("/")) {
            throw new IllegalArgumentException("Rate limit endpoint must be an absolute path");
        }
    }

    static void validateWindow(int maxRequests, long windowSizeMs) {
        if (maxRequests <= 0 || windowSizeMs <= 0) {
            throw new IllegalArgumentException("Window limit and size must be positive");
        }
    }
}
