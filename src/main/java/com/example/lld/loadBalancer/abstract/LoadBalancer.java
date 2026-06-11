package com.example.lld.loadBalancer.abstracts;

import java.util.*;

public abstract class LoadBalancer {

    protected final Map<RequestType, List<Server>> serverMap;

    protected LoadBalancer(Map<RequestType, List<Server>> serverMap) {
        this.serverMap = serverMap;
    }

    protected List<Server> getServers(RequestType requestType) {
        return serverMap.getOrDefault(requestType, List.of());
    }

    public abstract Destination getDestination(RequestType requestType);
}