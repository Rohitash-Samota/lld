package com.example.lld.ratelimiter.factory;

import com.example.lld.ratelimiter.config.EndpointRateLimitConfig;
import com.example.lld.ratelimiter.enums.RateLimitAlgorithm;
import com.example.lld.ratelimiter.implementation.SlidingWindowLogRateLimiter;
import com.example.lld.ratelimiter.implementation.TokenBucketRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

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
