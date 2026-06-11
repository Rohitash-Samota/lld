package com.example.lld.loadBalancer.dto;

public class Destination {
    private String ip;
    private int requestsBeingServed;
    private int threshold;

    public Destination(String ip, int threshold) {
        this.ip = ip;
        this.threshold = threshold;
    }

    public boolean acceptRequest() {
        if(requestsBeingServed < threshold) {
            requestsBeingServed++;
            return true;
        }
        return false;
    }

    public void completeRequest() {
        if(requestsBeingServed > 0) {
            requestsBeingServed--;
        }
    }

    public String getIp() {
        return ip;
    }

    public int getThreshold() {
        return threshold;
    }
    public int getRequestsBeingServed() {
        return requestsBeingServed;
    }
}
