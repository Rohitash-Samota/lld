package com.example.lld.loadBalancer.strategy.algorithm;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.example.lld.loadBalancer.abstracts.LoadBalancer;
import com.example.lld.loadBalancer.dto.Destination;
import com.example.lld.loadBalancer.dto.Server;
import com.example.lld.loadBalancer.enums.RequestType;

public class LeastConnectionsLoadBalancer extends LoadBalancer {

    public LeastConnectionsLoadBalancer(Map<RequestType, List<Server>> serverMap) {
        super(serverMap);
    }

    @Override
    public Destination getDestination(RequestType requestType) {

        List<Server> servers = getServers(requestType);

        if (servers.isEmpty()) {
            return null;
        }

        Server selected = servers.stream().min(Comparator.comparingInt(Server::getActiveConnections)).orElse(null);
        if (selected == null) {
            return null;
        }
        selected.incrementConnection();

        return selected.getDestination();
    }
}