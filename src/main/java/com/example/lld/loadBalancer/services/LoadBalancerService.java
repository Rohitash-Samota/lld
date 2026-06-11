package com.example.lld.loadBalancer.services;

public class LoadBalancerService {

    private static LoadBalancerService instance;
    
    private final ServerRegistry registry;

    private final LoadBalancingStrategy strategy;

    public LoadBalancerService(ServerRegistry registry,LoadBalancingStrategy strategy) {
        this.registry = registry;
        this.strategy = strategy;
    }

    public static LoadBalancerService getInstance(ServerRegistry registry,LoadBalancingStrategy strategy) {

        if(instance == null) {
            instance = new LoadBalancerService(registry, strategy);
        }

        return instance;
    }
    
    public static void setStrategy(LoadBalancingStrategy strategy) {
        if(instance == null) {
            throw new IllegalStateException("LoadBalancerService not initialized");
        }
        instance.strategy = strategy;
    }

    public Destination route(
            Request request) {

        List<Server> servers =
                registry.getHealthyServers();

        Server server =
                strategy.selectServer(
                        servers,
                        request
                );

        return server.getDestination();
    }
}