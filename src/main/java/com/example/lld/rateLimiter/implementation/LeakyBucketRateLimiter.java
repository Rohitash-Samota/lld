package com.example.lld.ratelimiter.implementation;

import java.util.concurrent.ConcurrentHashMap;

import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class LeakyBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private final double leakRatePerSecond;
    private final ConcurrentHashMap<String, BucketState> buckets = new ConcurrentHashMap<>();

    public LeakyBucketRateLimiter(int capacity, double leakRatePerSecond) {
        if (capacity <= 0 || leakRatePerSecond < 0) {
            throw new IllegalArgumentException("Capacity must be positive and leak rate cannot be negative");
        }
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
    }

    @Override
    public ResponseRateLimiter allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        BucketState bucket = buckets.computeIfAbsent(clientId, key -> new BucketState(now));

        synchronized (bucket) {
            long elapsedMs = now - bucket.lastUpdatedMs;
            bucket.queuedRequests = Math.max(0, bucket.queuedRequests - elapsedMs * leakRatePerSecond / 1000.0);
            bucket.lastUpdatedMs = now;

            if (bucket.queuedRequests + 1 > capacity) {
                Long retryAfterMs = leakRatePerSecond > 0
                        ? (long) Math.ceil((bucket.queuedRequests + 1 - capacity) * 1000.0 / leakRatePerSecond)
                        : null;
                return new ResponseRateLimiter(false, 0, retryAfterMs, "Rate limit exceeded");
            }

            bucket.queuedRequests++;
            int remaining = Math.max(0, (int) Math.floor(capacity - bucket.queuedRequests));
            return new ResponseRateLimiter(true, remaining, 0L, "Request Allowed");
        }
    }

    private static final class BucketState {
        private double queuedRequests;
        private long lastUpdatedMs;

        private BucketState(long lastUpdatedMs) {
            this.lastUpdatedMs = lastUpdatedMs;
        }
    }
}