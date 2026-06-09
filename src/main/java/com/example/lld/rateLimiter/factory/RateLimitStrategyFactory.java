package com.example.lld.rateLimiter.factory;

import com.example.lld.rateLimiter.enums.RateLimitType;
import com.example.lld.rateLimiter.interfaces.RateLimitKeyStrategy;
import com.example.lld.rateLimiter.strategy.key.DeviceKeyStrategy;
import com.example.lld.rateLimiter.strategy.key.IpKeyStrategy;
import com.example.lld.rateLimiter.strategy.key.UserKeyStrategy;

public class RateLimitStrategyFactory {
    private RateLimitStrategyFactory() {

    }

    public static RateLimitKeyStrategy getStrategy(
            RateLimitType type) {

        switch (type) {

            case IP:
                return new IpKeyStrategy();

            case USER:
                return new UserKeyStrategy();

            case DEVICE:
                return new DeviceKeyStrategy();

            default:
                throw new IllegalArgumentException(
                        "Unsupported RateLimitType");
        }
    }
}
