package com.example.lld.logisticsFactory;
public class SeeLogistic  implements LogisticsI{
    @Override
    public void planDelivery(Product p){
        System.out.println("Product Details name : " + p.getProductName()+" Desc : "+ p.getProductDesc());
        System.out.println("Plan Delivery using  See Logistic");
    }

    @Override
    public void createTransport(Product p){
        System.out.println("Product Details name : " + p.getProductName()+" Desc : "+ p.getProductDesc());
        System.out.println("Create Transport using  See Logistic");
    } 
}
