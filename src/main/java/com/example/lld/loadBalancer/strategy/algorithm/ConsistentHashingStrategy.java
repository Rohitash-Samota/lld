package com.example.lld.loadBalancer.strategy.algorithm;

public class ConsistentHashingStrategy extends LoadBalancer {

    private final TreeMap<Integer, Server> ring = new TreeMap<>();

    public ConsistentHashingStrategy(List<Server> servers) {

        for(Server server : servers) {
            ring.put(server.getId().hashCode(),server);
        }
    }

    @Override
    public Server selectServer(List<Server> servers, Request request) {

        int hash = request.getUserId().hashCode();

        Map.Entry<Integer, Server> entry = ring.ceilingEntry(hash);

        if(entry == null) {
            entry = ring.firstEntry();
        }

        return entry.getValue();
    }
}