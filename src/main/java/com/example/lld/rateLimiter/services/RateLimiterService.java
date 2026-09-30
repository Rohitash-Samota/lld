package com.example.lld.ratelimiter.services;

import java.util.HashMap;
import java.util.Map;

import com.example.lld.ratelimiter.config.EndpointRateLimitConfig;
import com.example.lld.ratelimiter.config.RateLimiterConfiguration;
import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.factory.RateLimiterFactory;
import com.example.lld.ratelimiter.interfaces.RateLimiter;

public class RateLimiterService {

    private static final RateLimiterConfiguration CONFIG = RateLimiterConfiguration.load();

    private final Map<String, RateLimiter> endpointLimiters = new HashMap<>();
    private final RateLimiter defaultLimiter;

    public RateLimiterService() {
        CONFIG.getEndpointConfigs().forEach((endpoint, endpointConfig) -> endpointLimiters.put(endpoint,
                RateLimiterFactory.createLimiter(endpointConfig)));

        EndpointRateLimitConfig defaultConfig = CONFIG.getDefaultConfig();
        defaultLimiter = RateLimiterFactory.createLimiter(defaultConfig);
    }

    public ResponseRateLimiter allowRequest(String clientId, String endpoint) {
        if (clientId == null || clientId.isBlank()) {
            return new ResponseRateLimiter(false, 0, null, "Missing clientId");
        }

        RateLimiter limiter = endpointLimiters.getOrDefault(endpoint, defaultLimiter);
        return limiter.allowRequest(clientId);
    }
}
