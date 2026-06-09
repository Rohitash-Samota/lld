package com.example.lld.rateLimiter.factory;

import com.example.lld.rateLimiter.config.EndpointRateLimitConfig;
import com.example.lld.rateLimiter.enums.RateLimitAlgorithm;
import com.example.lld.rateLimiter.implementation.SlidingWindowLogRateLimiter;
import com.example.lld.rateLimiter.implementation.TokenBucketRateLimiter;
import com.example.lld.rateLimiter.interfaces.RateLimiter;

public class RateLimiterFactory {

    public static RateLimiter createLimiter(EndpointRateLimitConfig config) {
        RateLimitAlgorithm algorithm = config.getAlgorithm();
        switch (algorithm) {
            case TokenBucket:
                return new TokenBucketRateLimiter(config.getCapacity(), config.getRefillRatePerSecond());
            case SlidingWindowLog:
                return new SlidingWindowLogRateLimiter(config.getMaxRequests(), config.getWindowSizeMs());
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }
    }
}
