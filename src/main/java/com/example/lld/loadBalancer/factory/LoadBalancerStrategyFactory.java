package com.example.lld.loadBalancer.factory;

import java.util.List;
import java.util.Map;

import com.example.lld.loadBalancer.abstracts.LoadBalancer;
import com.example.lld.loadBalancer.dto.Server;
import com.example.lld.loadBalancer.enums.LoadBalancerType;
import com.example.lld.loadBalancer.enums.RequestType;
import com.example.lld.loadBalancer.strategy.algorithm.IPHashLoadBalancer;
import com.example.lld.loadBalancer.strategy.algorithm.LeastConnectionsLoadBalancer;
import com.example.lld.loadBalancer.strategy.algorithm.RoundRobinLoadBalancer;
import com.example.lld.loadBalancer.strategy.algorithm.WeightedDistribution;

public class LoadBalancerStrategyFactory {
    public static LoadBalancer createLoadBalancer(LoadBalancerType type, Map<RequestType, List<Server>> serverMap) {
        switch (type) {
            case ROUND_ROBIN -> {
                return new RoundRobinLoadBalancer(serverMap);
            }
            case LEAST_CONNECTIONS -> {
                return new LeastConnectionsLoadBalancer(serverMap);
            }
            case IP_HASH -> {
                return new IPHashLoadBalancer(serverMap);
            }
            case WEIGHTED_DISTRIBUTION -> {
                return new WeightedDistribution(serverMap);
            }
            default -> throw new IllegalArgumentException("Invalid Load Balancer Type");
        }
    }
}
