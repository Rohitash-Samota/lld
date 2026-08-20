package com.example.lld.logisticsFactory;

public class Product {
    private String productName;
    private String productDesc;

    public Product(String productName,String productDesc){
        this.productName = productName;
        this.productDesc = productDesc;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return this.productDesc;
    }
    
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
