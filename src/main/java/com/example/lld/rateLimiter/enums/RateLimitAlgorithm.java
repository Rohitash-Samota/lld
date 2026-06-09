package com.example.lld.rateLimiter.enums;

public enum RateLimitAlgorithm {
    TokenBucket,
    SlidingWindowLog;

    public static RateLimitAlgorithm from(String algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("Algorithm cannot be null");
        }
        for (RateLimitAlgorithm value : values()) {
            if (value.name().equalsIgnoreCase(algorithm)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
    }
}
