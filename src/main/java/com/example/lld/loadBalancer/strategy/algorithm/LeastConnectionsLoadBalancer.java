package com.example.lld.loadBalancer.strategy.algorithm;

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
        selected.incrementActiveConnections();

        return selected.getDestination();
    }
}