package com.example.lld.rateLimiter.strategy.key;

import com.example.lld.rateLimiter.dto.RequestRateLimiter;
import com.example.lld.rateLimiter.interfaces.RateLimitKeyStrategy;

public class IpKeyStrategy implements RateLimitKeyStrategy {
    @Override
    public String generateKey(RequestRateLimiter request) {
        return "IP-" + request.getIp();
    }
}
