package com.example.lld.inventoryManagement.dto;

import com.example.lld.inventoryManagement.enums.ProductType;

public class Stock {
    private String stockId;
    private String stockName;
    private ProductType productType;
    private int productQuantity;

    public Stock(String stockId, String stockName, ProductType productType, int productQuantity) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.productType = productType;
        this.productQuantity = productQuantity;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

}
