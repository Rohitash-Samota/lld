package com.example.lld.rateLimiter.dto;

public class RateLimitResult {
    private boolean allowed;
    private Long remainingTokens;
    private Long retryAfterSecond;

    public RateLimitResult(boolean allowed, Long remainingTokens, Long retryAfterSecond) {
        this.allowed = allowed;
        this.remainingTokens = remainingTokens;
        this.retryAfterSecond = retryAfterSecond;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public Long getRemainingTokens() {
        return remainingTokens;
    }

    public void setRemainingTokens(Long remainingTokens) {
        this.remainingTokens = remainingTokens;
    }

    public Long getRetryAfterSecond() {
        return retryAfterSecond;
    }

    public void setRetryAfterSecond(Long retryAfterSecond) {
        this.retryAfterSecond = retryAfterSecond;
    }

}
