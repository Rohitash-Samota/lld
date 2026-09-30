package com.example.lld.ratelimiter.factory;

import com.example.lld.ratelimiter.enums.RateLimitType;
import com.example.lld.ratelimiter.interfaces.RateLimitKeyStrategy;
import com.example.lld.ratelimiter.strategy.key.DeviceKeyStrategy;
import com.example.lld.ratelimiter.strategy.key.IpKeyStrategy;
import com.example.lld.ratelimiter.strategy.key.UserKeyStrategy;

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
