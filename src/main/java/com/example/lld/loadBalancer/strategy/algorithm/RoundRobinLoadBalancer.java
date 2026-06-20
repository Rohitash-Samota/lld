package com.example.lld.loadBalancer.strategy.algorithm;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.lld.loadBalancer.abstracts.LoadBalancer;
import com.example.lld.loadBalancer.dto.Destination;
import com.example.lld.loadBalancer.dto.Server;
import com.example.lld.loadBalancer.enums.RequestType;

public class RoundRobinLoadBalancer extends LoadBalancer {

    private final Map<RequestType, AtomicInteger> counters = new ConcurrentHashMap<>();

    public RoundRobinLoadBalancer(Map<RequestType, List<Server>> serverMap) {
        super(serverMap);

        for (RequestType type : RequestType.values()) {
            counters.put(type, new AtomicInteger());
        }
    }

    @Override
    public Destination getDestination(RequestType requestType) {

        if (requestType == null) {
            return null;
        }

        List<Server> servers = getServers(requestType);

        if (servers.isEmpty()) {
            return null;
        }

        AtomicInteger counter = counters.computeIfAbsent(requestType, k -> new AtomicInteger());
        int index = Math.floorMod(counter.getAndIncrement(), servers.size());

        Server selected = servers.get(index);
        return selected == null ? null : selected.getDestination();
    }
}