package com.example.lld.rateLimiter.dto;

public class Bucket {

    private long tokens;
    private final long capacity;
    private final double refillRatePerSecond;
    private long lastRefillTimeMillis;

    public Bucket(long capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.tokens = capacity;
        this.lastRefillTimeMillis = System.currentTimeMillis();
    }

    public synchronized ResponseRateLimiter tryConsume() {
        refill();

        if (tokens > 0) {
            tokens--;
            return new ResponseRateLimiter(
                    true,
                    (int) tokens,
                    0L,
                    "Request Allowed");
        }

        Long retryAfterMs = refillRatePerSecond > 0
                ? (long) Math.ceil(1000.0 / refillRatePerSecond)
                : null;

        return new ResponseRateLimiter(
                false,
                0,
                retryAfterMs,
                "Rate limit exceeded");
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedMs = now - lastRefillTimeMillis;

        if (elapsedMs <= 0 || refillRatePerSecond <= 0) {
            return;
        }

        double tokensToAdd = (elapsedMs / 1000.0) * refillRatePerSecond;
        if (tokensToAdd >= 1.0) {
            tokens = Math.min(capacity, tokens + (long) tokensToAdd);
            lastRefillTimeMillis = now;
        }
    }
}