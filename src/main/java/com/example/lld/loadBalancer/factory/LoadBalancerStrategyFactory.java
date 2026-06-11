package com.example.lld.loadBalancer.factory;

public class LoadBalancerStrategyFactory {
    public static LoadBalancer createLoadBalancer(LoadBalancerType type, Map<RequestType, List<Server>> serverMap) {
        switch (type) {
            case ROUND_ROBIN:
                return new RoundRobinLoadBalancer(serverMap);
            case LEAST_CONNECTIONS:
                return new LeastConnectionsLoadBalancer(serverMap);
            case IP_HASH:
                return new IPHashLoadBalancer(serverMap);
            case WEIGHTED_DISTRIBUTION:
                return new WeightedDistribution(serverMap);
            default:
                throw new IllegalArgumentException("Invalid Load Balancer Type");
        }
    }
}
