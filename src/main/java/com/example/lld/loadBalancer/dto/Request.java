package com.example.lld.loadBalancer.dto;

import com.example.lld.loadBalancer.enums.RequestType;

public class Request {

    private String userId;
    private RequestType requestType;

    public Request(String userId, RequestType requestType) {
        this.userId = userId;
        this.requestType = requestType;
    }

    public String getUserId() {
        return userId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}