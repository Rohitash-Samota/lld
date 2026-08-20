package com.example.lld.logisticsFactory;

public class RoadLogistics implements LogisticsI {
    @Override
    public void planDelivery(Product p){
        System.out.println("Product Details name : " + p.getProductName()+" Desc : "+ p.getProductDesc());
        System.out.println("Plan Delivery using  RoadLogistics");
    }
    @Override
    public void createTransport(Product p){
        System.out.println("Product Details name : " + p.getProductName()+" Desc : "+ p.getProductDesc());
        System.out.println("Create Transport using  RoadLogistics");
    }
}
