package com.example.lld.rateLimiter.strategy.key;

import com.example.lld.rateLimiter.dto.RequestRateLimiter;
import com.example.lld.rateLimiter.interfaces.RateLimitKeyStrategy;

public class UserKeyStrategy implements RateLimitKeyStrategy {
    @Override
    public String generateKey(RequestRateLimiter request) {
        return "UI-" + request.getUserId();
    }
}
