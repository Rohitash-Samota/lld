package com.example.lld.ratelimiter.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rate-limit")
public record RateLimitProperties(
        boolean enabled,
        boolean failOpen,
        List<TokenBucketRateLimitConfig> tokenBucket,
        List<FixedWindowCounterRateLimitConfig> fixedWindowCounter,
        List<SlidingWindowCounterRateLimitConfig> slidingWindowCounter,
        List<SlidingWindowLogRateLimitConfig> slidingWindowLog,
        List<LeakyBucketRateLimitConfig> leakyBucket) {

    public RateLimitProperties {
        tokenBucket = safeCopy(tokenBucket);
        fixedWindowCounter = safeCopy(fixedWindowCounter);
        slidingWindowCounter = safeCopy(slidingWindowCounter);
        slidingWindowLog = safeCopy(slidingWindowLog);
        leakyBucket = safeCopy(leakyBucket);
    }

    public Map<String, EndpointRateLimitConfig> endpointConfigs() {
        List<EndpointRateLimitConfig> policies = new ArrayList<>();
        policies.addAll(tokenBucket);
        policies.addAll(fixedWindowCounter);
        policies.addAll(slidingWindowCounter);
        policies.addAll(slidingWindowLog);
        policies.addAll(leakyBucket);

        Map<String, EndpointRateLimitConfig> configs = new LinkedHashMap<>();
        for (EndpointRateLimitConfig policy : policies) {
            if (configs.putIfAbsent(policy.endpoint(), policy) != null) {
                throw new IllegalArgumentException("Duplicate rate limit endpoint: " + policy.endpoint());
            }
        }
        return Map.copyOf(configs);
    }

    private static <T> List<T> safeCopy(List<T> values) {
        return values == null ? List.of() : List.copyOf(values);
    }
}
