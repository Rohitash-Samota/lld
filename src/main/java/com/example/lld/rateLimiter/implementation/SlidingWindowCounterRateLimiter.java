package com.example.lld.ratelimiter.implementation;

import java.util.concurrent.ConcurrentHashMap;

import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class SlidingWindowCounterRateLimiter implements RateLimiter {

    private final int maxRequests;
    private final long windowSizeMs;
    private final ConcurrentHashMap<String, WindowCounter> counters = new ConcurrentHashMap<>();

    public SlidingWindowCounterRateLimiter(int maxRequests, long windowSizeMs) {
        if (maxRequests <= 0 || windowSizeMs <= 0) {
            throw new IllegalArgumentException("Request limit and window size must be positive");
        }
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }

    @Override
    public ResponseRateLimiter allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        long currentWindow = Math.floorDiv(now, windowSizeMs) * windowSizeMs;
        WindowCounter counter = counters.computeIfAbsent(clientId, key -> new WindowCounter(currentWindow));

        synchronized (counter) {
            if (currentWindow > counter.windowStartMs) {
                long windowsElapsed = (currentWindow - counter.windowStartMs) / windowSizeMs;
                counter.previousCount = windowsElapsed == 1 ? counter.currentCount : 0;
                counter.currentCount = 0;
                counter.windowStartMs = currentWindow;
            }

            long elapsedInWindow = now - currentWindow;
            double previousWindowWeight = 1.0 - (double) elapsedInWindow / windowSizeMs;
            double estimatedCount = counter.currentCount + counter.previousCount * previousWindowWeight;

            if (estimatedCount < maxRequests) {
                counter.currentCount++;
                int remaining = Math.max(0, maxRequests - (int) Math.ceil(counter.currentCount
                        + counter.previousCount * previousWindowWeight));
                return new ResponseRateLimiter(true, remaining, 0L, "Request Allowed");
            }

            return new ResponseRateLimiter(false, 0, windowSizeMs - elapsedInWindow,
                    "Rate limit exceeded");
        }
    }

    private static final class WindowCounter {
        private long windowStartMs;
        private int currentCount;
        private int previousCount;

        private WindowCounter(long windowStartMs) {
            this.windowStartMs = windowStartMs;
        }
    }
}