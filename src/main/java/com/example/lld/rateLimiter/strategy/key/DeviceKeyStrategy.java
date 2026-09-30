package com.example.lld.ratelimiter.strategy.key;

import com.example.lld.ratelimiter.dto.RequestRateLimiter;
import com.example.lld.ratelimiter.interfaces.RateLimitKeyStrategy;

public class DeviceKeyStrategy implements RateLimitKeyStrategy {
    @Override
    public String generateKey(RequestRateLimiter request) {
        return "D-" + request.getDeviceId();
    }
}
