package com.example.lld.ratelimiter.implementation;

import java.util.concurrent.ConcurrentHashMap;

import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class FixedWindowCounterRateLimiter implements RateLimiter {

    private final int maxRequests;
    private final long windowSizeMs;
    private final ConcurrentHashMap<String, WindowCounter> counters = new ConcurrentHashMap<>();

    public FixedWindowCounterRateLimiter(int maxRequests, long windowSizeMs) {
        if (maxRequests <= 0 || windowSizeMs <= 0) {
            throw new IllegalArgumentException("Request limit and window size must be positive");
        }
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }

    @Override
    public ResponseRateLimiter allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        long windowStart = Math.floorDiv(now, windowSizeMs) * windowSizeMs;
        WindowCounter counter = counters.computeIfAbsent(clientId, key -> new WindowCounter(windowStart));

        synchronized (counter) {
            if (counter.windowStartMs != windowStart) {
                counter.windowStartMs = windowStart;
                counter.count = 0;
            }

            long retryAfterMs = windowStart + windowSizeMs - now;
            if (counter.count >= maxRequests) {
                return new ResponseRateLimiter(false, 0, retryAfterMs, "Rate limit exceeded");
            }

            counter.count++;
            return new ResponseRateLimiter(true, maxRequests - counter.count, 0L, "Request Allowed");
        }
    }

    private static final class WindowCounter {
        private long windowStartMs;
        private int count;

        private WindowCounter(long windowStartMs) {
            this.windowStartMs = windowStartMs;
        }
    }
}