package com.example.lld.ratelimiter.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.lld.ratelimiter.config.RateLimitProperties;
import com.example.lld.ratelimiter.dto.ResponseRateLimiter;
import com.example.lld.ratelimiter.services.RedisRateLimiterService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

@Component
@ConditionalOnProperty(prefix = "rate-limit", name = "enabled", havingValue = "true")
public class RateLimitFilter extends OncePerRequestFilter {

    private final RedisRateLimiterService rateLimiterService;
    private final RateLimitProperties properties;
    private final ObjectMapper objectMapper;

    public RateLimitFilter(RedisRateLimiterService rateLimiterService,
            RateLimitProperties properties,
            ObjectMapper objectMapper) {
        this.rateLimiterService = rateLimiterService;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String endpoint = endpointPath(request);
        if (!rateLimiterService.isConfigured(endpoint)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            ResponseRateLimiter result = rateLimiterService.allowRequest(clientId(request), endpoint);
            response.setHeader("RateLimit-Limit", Integer.toString(rateLimiterService.getLimit(endpoint)));
            response.setHeader("RateLimit-Remaining", Integer.toString(result.getRemaining()));
            if (!result.isAllowed()) {
                response.setStatus(429);
                response.setContentType("application/json");
                if (result.getRetryAfterMs() != null) {
                    long retryAfterSeconds = Math.max(1, (result.getRetryAfterMs() + 999) / 1000);
                    response.setHeader("Retry-After", Long.toString(retryAfterSeconds));
                }
                objectMapper.writeValue(response.getOutputStream(), result);
                return;
            }
        } catch (DataAccessException exception) {
            if (properties.failOpen()) {
                filterChain.doFilter(request, response);
                return;
            }
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.setContentType("application/json");
            response.setHeader("Retry-After", "1");
            Map<String, Object> error = new LinkedHashMap<>();
            error.put("message", "Rate limit service unavailable");
            objectMapper.writeValue(response.getOutputStream(), error);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String endpointPath(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        return contextPath.isEmpty() ? requestUri : requestUri.substring(contextPath.length());
    }

    private String clientId(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null && !principal.getName().isBlank()) {
            return principal.getName();
        }
        return request.getRemoteAddr();
    }
}