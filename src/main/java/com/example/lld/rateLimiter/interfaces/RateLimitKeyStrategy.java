package com.example.lld.ratelimiter.interfaces;

import com.example.lld.ratelimiter.dto.RequestRateLimiter;

public interface RateLimitKeyStrategy {
    String generateKey(RequestRateLimiter request);
}
