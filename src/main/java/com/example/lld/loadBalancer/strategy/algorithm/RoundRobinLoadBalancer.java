package com.example.lld.loadBalancer.strategy.algorithm;

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

        List<Server> servers = getServers(requestType);

        if (servers.isEmpty()) {
            return null;
        }

        int index = Math.abs( counters.get(requestType).getAndIncrement()) % servers.size();

        return servers.get(index).getDestination();
    }
}