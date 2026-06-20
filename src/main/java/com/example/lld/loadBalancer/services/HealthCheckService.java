package com.example.lld.loadBalancer.services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.lld.loadBalancer.dto.Server;
import com.example.lld.loadBalancer.registry.ServerRegistry;

public class HealthCheckService {

    private static HealthCheckService instance;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static HealthCheckService getInstance() {
        if (instance == null) {
            instance = new HealthCheckService();
        }
        return instance;
    }

    public void start(ServerRegistry registry) {

        scheduler.scheduleAtFixedRate(
                () -> {

                    for (Server server : registry.getHealthyServers()) {

                        boolean alive = ping(server);

                        if (!alive) {
                            server.setHealthy(false);
                        }
                    }

                },
                0,
                10,
                TimeUnit.SECONDS);
    }

    private boolean ping(Server server) {
        return true;
    }
}