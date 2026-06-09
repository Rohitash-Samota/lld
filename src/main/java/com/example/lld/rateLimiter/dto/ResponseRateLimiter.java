package com.example.lld.rateLimiter.dto;

public class ResponseRateLimiter {
    private boolean allowed;
    private Long remainingRequests;
    private Long retryAfterSeconds;
    private Object message;

    public ResponseRateLimiter(boolean allowed, Long remainingRequests, Long retryAfterSeconds, Object message) {
        this.allowed = allowed;
        this.remainingRequests = remainingRequests;
        this.retryAfterSeconds = retryAfterSeconds;
        this.message = message;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public Long getRemainingRequests() {
        return remainingRequests;
    }

    public void setRemainingRequests(Long remainingRequests) {
        this.remainingRequests = remainingRequests;
    }

    public Long getRetryAfterSeconds() {
        return retryAfterSeconds;
    }

    public void setRetryAfterSeconds(Long retryAfterSeconds) {
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

}
