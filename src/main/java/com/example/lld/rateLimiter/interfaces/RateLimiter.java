package com.example.lld.ratelimiter.interfaces;

import com.example.lld.ratelimiter.dto.ResponseRateLimiter;

public interface RateLimiter {
    ResponseRateLimiter allowRequest(String clientId);
}
