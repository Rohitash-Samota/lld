package com.example.lld.ratelimiter.implementation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class SlidingWindowLogRateLimiter implements RateLimiter {

    private final int maxRequests;
    private final long windowSizeMs;
    private final ConcurrentHashMap<String, Deque<Long>> requestLogs = new ConcurrentHashMap<>();

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSizeMs) {
        this.maxRequests = maxRequests;
        this.windowSizeMs = windowSizeMs;
    }

    @Override
    public ResponseRateLimiter allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        Deque<Long> timestamps = requestLogs.computeIfAbsent(clientId, key -> new ArrayDeque<>());

        synchronized (timestamps) {
            while (!timestamps.isEmpty() && now - timestamps.peekFirst() >= windowSizeMs) {
                timestamps.pollFirst();
            }

            if (timestamps.size() < maxRequests) {
                timestamps.addLast(now);
                return new ResponseRateLimiter(true, maxRequests - timestamps.size(), 0L, "Request Allowed");
            }

            long retryAfterMs = windowSizeMs - (now - timestamps.peekFirst());
            return new ResponseRateLimiter(false, 0, retryAfterMs, "Rate limit exceeded");
        }
    }
}
