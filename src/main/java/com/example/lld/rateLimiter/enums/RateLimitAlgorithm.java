package com.example.lld.ratelimiter.enums;

public enum RateLimitAlgorithm {
    TOKEN_BUCKET,
    FIXED_WINDOW_COUNTER,
    SLIDING_WINDOW_COUNTER,
    SLIDING_WINDOW_LOG,
    LEAKY_BUCKET;

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
