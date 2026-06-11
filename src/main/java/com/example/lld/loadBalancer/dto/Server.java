package com.example.lld.loadBalancer.dto;

public class Server {

    private String id;
    private Destination destination;
    private int weight;

    private AtomicInteger activeConnections = new AtomicInteger(0);

    private volatile boolean healthy = true;

    public int getActiveConnections() {
        return activeConnections.get();
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
}