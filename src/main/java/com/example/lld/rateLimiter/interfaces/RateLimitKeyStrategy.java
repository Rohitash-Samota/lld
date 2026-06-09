package com.example.lld.rateLimiter.interfaces;

import com.example.lld.rateLimiter.dto.RequestRateLimiter;

public interface RateLimitKeyStrategy {
    String generateKey(RequestRateLimiter request);
}
