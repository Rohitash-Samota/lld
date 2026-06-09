package com.example.lld.rateLimiter.interfaces;

import com.example.lld.rateLimiter.dto.ResponseRateLimiter;

public interface RateLimiter {
    ResponseRateLimiter allowRequest(String clientId);
}
