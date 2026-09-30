package com.example.lld.ratelimiter.implementation;

import java.util.concurrent.ConcurrentHashMap;

import com.example.lld.ratelimiter.dto.Bucket;
import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

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