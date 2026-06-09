package com.example.lld.rateLimiter.implementation;

import com.example.lld.rateLimiter.dto.Bucket;
import com.example.lld.rateLimiter.dto.ResponseRateLimiter;
import com.example.lld.rateLimiter.interfaces.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {

    private final long capacity;
    private final double refillRatePerSecond;
    private final ConcurrentHashMap<String, Bucket> bucketStore = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public ResponseRateLimiter allowRequest(String clientId) {
        Bucket bucket = bucketStore.computeIfAbsent(
                clientId,
                k -> new Bucket(capacity, refillRatePerSecond));

        return bucket.tryConsume();
    }
}