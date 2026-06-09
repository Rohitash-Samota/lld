package com.example.lld.rateLimiter.config;

import com.example.lld.rateLimiter.enums.RateLimitAlgorithm;

public class EndpointRateLimitConfig {

    private final String endpoint;
    private final RateLimitAlgorithm algorithm;
    private final int capacity;
    private final double refillRatePerSecond;
    private final int maxRequests;
    private final long windowSizeMs;

    public EndpointRateLimitConfig(String endpoint,
            RateLimitAlgorithm algorithm,
            int capacity,
            double refillRatePerSecond,
            int maxRequests,
            long windowSizeMs) {
        this.endpoint = endpoint;
        this.algorithm = algorithm;
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public RateLimitAlgorithm getAlgorithm() {
        return algorithm;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRefillRatePerSecond() {
        return refillRatePerSecond;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMs() {
        return windowSizeMs;
    }
}
