package com.example.lld.rateLimiter.dto;

public class RequestRateLimiter {
    private String ip;
    private String userId;
    private String deviceId;
    private String endpoint;
    private String token;
    private Object body;

    public RequestRateLimiter(String ip, String userId, String deviceId, String endpoint, String token, Object body) {
        this.ip = ip;
        this.userId = userId;
        this.deviceId = deviceId;
        this.endpoint = endpoint;
        this.token = token;
        this.body = body;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
