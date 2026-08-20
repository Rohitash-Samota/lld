package com.example.lld.logisticsFactory;

public class LogisticsFactoryOp {
    public LogisticsI getLogistics(TransPortType transPortType){
        if(transPortType==null){
            return null;
        }
        return switch (transPortType) {
            case TransPortType.ROAD -> new RoadLogistics();
            case TransPortType.SEE -> new SeeLogistic();
            default -> null;
        };
    }
}
