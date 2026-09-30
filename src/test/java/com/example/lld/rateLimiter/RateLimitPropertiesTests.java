package com.example.lld.ratelimiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;

import com.example.lld.ratelimiter.config.FixedWindowCounterRateLimitConfig;
import com.example.lld.ratelimiter.config.LeakyBucketRateLimitConfig;
import com.example.lld.ratelimiter.config.RateLimitProperties;
import com.example.lld.ratelimiter.config.SlidingWindowCounterRateLimitConfig;
import com.example.lld.ratelimiter.config.SlidingWindowLogRateLimitConfig;
import com.example.lld.ratelimiter.config.TokenBucketRateLimitConfig;

class RateLimitPropertiesTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(PropertiesConfiguration.class)
            .withPropertyValues(
                    "rate-limit.enabled=true",
                    "rate-limit.fail-open=false",
                    "rate-limit.token-bucket[0].endpoint=/api/login",
                    "rate-limit.token-bucket[0].capacity=8",
                    "rate-limit.token-bucket[0].refill-rate-per-second=2.0",
                    "rate-limit.fixed-window-counter[0].endpoint=/api/fixed",
                    "rate-limit.fixed-window-counter[0].max-requests=10",
                    "rate-limit.fixed-window-counter[0].window-size-ms=60000",
                    "rate-limit.sliding-window-counter[0].endpoint=/api/profile",
                    "rate-limit.sliding-window-counter[0].max-requests=20",
                    "rate-limit.sliding-window-counter[0].window-size-ms=120000",
                    "rate-limit.sliding-window-log[0].endpoint=/api/search",
                    "rate-limit.sliding-window-log[0].max-requests=30",
                    "rate-limit.sliding-window-log[0].window-size-ms=180000",
                    "rate-limit.leaky-bucket[0].endpoint=/api/export",
                    "rate-limit.leaky-bucket[0].capacity=4",
                    "rate-limit.leaky-bucket[0].leak-rate-per-second=0.5");

    @Test
    void bindsDistinctAlgorithmPropertiesToEndpointConfigs() {
        contextRunner.run(context -> {
            assertTrue(context.isRunning());
            RateLimitProperties properties = context.getBean(RateLimitProperties.class);
            var endpoints = properties.endpointConfigs();

            assertEquals(5, endpoints.size());
                assertTrue(endpoints.get("/api/login") instanceof TokenBucketRateLimitConfig);
                assertEquals(8, ((TokenBucketRateLimitConfig) endpoints.get("/api/login")).capacity());
                assertTrue(endpoints.get("/api/fixed") instanceof FixedWindowCounterRateLimitConfig);
                assertTrue(endpoints.get("/api/profile") instanceof SlidingWindowCounterRateLimitConfig);
                assertEquals(20,
                    ((SlidingWindowCounterRateLimitConfig) endpoints.get("/api/profile")).maxRequests());
                assertTrue(endpoints.get("/api/search") instanceof SlidingWindowLogRateLimitConfig);
                assertTrue(endpoints.get("/api/export") instanceof LeakyBucketRateLimitConfig);
            assertFalse(properties.failOpen());
        });
    }

    @Configuration(proxyBeanMethods = false)
    @EnableConfigurationProperties(RateLimitProperties.class)
    static class PropertiesConfiguration {
    }
}