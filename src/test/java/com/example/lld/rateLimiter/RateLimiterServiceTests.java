package com.example.lld.rateLimiter;

import com.example.lld.rateLimiter.dto.ResponseRateLimiter;
import com.example.lld.rateLimiter.services.RateLimiterService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateLimiterServiceTests {

    private final RateLimiterService service = new RateLimiterService();

    @Test
    void allowRequest_allowsUpToCapacityForTokenBucket() {
        String clientId = "client-A";
        String endpoint = "/api/login";

        for (int i = 0; i < 5; i++) {
            ResponseRateLimiter result = service.allowRequest(clientId, endpoint);
            assertTrue(result.isAllowed(), "Expected allowed for request " + i);
            assertNotNull(result.getRemaining());
        }
    }

    @Test
    void allowRequest_deniesWhenTokenBucketCapacityExceeded() {
        String clientId = "client-B";
        String endpoint = "/api/login";

        for (int i = 0; i < 5; i++) {
            service.allowRequest(clientId, endpoint);
        }

        ResponseRateLimiter denied = service.allowRequest(clientId, endpoint);

        assertFalse(denied.isAllowed());
        assertNotNull(denied.getRetryAfterMs());
    }

    @Test
    void allowRequest_allowsUpToMaxRequestsForSlidingWindow() {
        String clientId = "client-C";
        String endpoint = "/api/search";

        for (int i = 0; i < 10; i++) {
            ResponseRateLimiter result = service.allowRequest(clientId, endpoint);
            assertTrue(result.isAllowed(), "Expected allowed for request " + i);
        }
    }

    @Test
    void allowRequest_deniesWhenSlidingWindowLogLimitReached() {
        String clientId = "client-D";
        String endpoint = "/api/search";

        for (int i = 0; i < 10; i++) {
            service.allowRequest(clientId, endpoint);
        }

        ResponseRateLimiter denied = service.allowRequest(clientId, endpoint);

        assertFalse(denied.isAllowed());
        assertNotNull(denied.getRetryAfterMs());
    }

    @Test
    void allowRequest_usesDefaultConfigWhenEndpointUnconfigured() {
        String clientId = "client-E";
        String endpoint = "/api/unknown";

        ResponseRateLimiter result = service.allowRequest(clientId, endpoint);

        assertTrue(result.isAllowed());
        assertNotNull(result.getRemaining());
    }
}
