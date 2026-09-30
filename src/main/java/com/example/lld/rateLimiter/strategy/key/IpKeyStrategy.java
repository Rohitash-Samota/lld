package com.example.lld.ratelimiter.strategy.key;

import com.example.lld.ratelimiter.dto.RequestRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimitKeyStrategy;

public class IpKeyStrategy implements RateLimitKeyStrategy {
    @Override
    public String generateKey(RequestRateLimiter request) {
        return "IP-" + request.getIp();
    }
}
