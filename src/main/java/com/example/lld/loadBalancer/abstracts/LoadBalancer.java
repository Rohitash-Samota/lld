package com.example.lld.loadBalancer.abstracts;

import java.util.List;
import java.util.Map;

import com.example.lld.loadBalancer.dto.Destination;
import com.example.lld.loadBalancer.dto.Server;
import com.example.lld.loadBalancer.enums.RequestType;

public abstract class LoadBalancer {

    protected final Map<RequestType, List<Server>> serverMap;

    protected LoadBalancer(Map<RequestType, List<Server>> serverMap) {
        this.serverMap = serverMap;
    }

    protected List<Server> getServers(RequestType requestType) {
        return serverMap.getOrDefault(requestType, List.of()).stream()
                .filter(Server::isHealthy)
                .toList();
    }

    public abstract Destination getDestination(RequestType requestType);
}