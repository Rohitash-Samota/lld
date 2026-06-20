package com.example.lld.loadBalancer.services;

import com.example.lld.loadBalancer.abstracts.LoadBalancer;
import com.example.lld.loadBalancer.dto.Destination;
import com.example.lld.loadBalancer.dto.Request;

public class LoadBalancerService {

    private static LoadBalancerService instance;

    private final LoadBalancer loadBalancer;

    public LoadBalancerService(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    public static LoadBalancerService getInstance(LoadBalancer loadBalancer) {
        if (instance == null) {
            instance = new LoadBalancerService(loadBalancer);
        }
        return instance;
    }

    public Destination route(Request request) {
        if (request == null || request.getRequestType() == null) {
            return null;
        }

        return loadBalancer.getDestination(request.getRequestType());
    }
}