package com.example.lld.logisticsFactory;

import org.springframework.stereotype.Service;

@Service("logisticsService")
public class LogisticsService {

    private final LogisticsFactoryOp logisticsFactoryOp;

    public LogisticsService(LogisticsFactoryOp logisticsFactoryOp) {
        this.logisticsFactoryOp = logisticsFactoryOp;
    }

    public void processDelivery(Product product, TransPortType type) {

        LogisticsI logistics = logisticsFactoryOp.getLogistics(type);

        logistics.planDelivery(product);
        logistics.createTransport(product);
    }
}