package com.example.lld.loadBalancer.dto;

import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    private String id;
    private Destination destination;
    private int weight;

    private final AtomicInteger activeConnections = new AtomicInteger(0);

    private volatile boolean healthy = true;

    public int getActiveConnections() {
        return activeConnections.get();
    }

    public int getWeight() {
        return this.weight;
    }

    public void incrementConnection() {
        activeConnections.incrementAndGet();
    }

    public void decrementConnection() {
        activeConnections.decrementAndGet();
    }

    public boolean isHealthy() {
        return healthy;
    }

    public String getId() {
        return this.id;
    }

    public Destination getDestination() {
        return this.destination;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}