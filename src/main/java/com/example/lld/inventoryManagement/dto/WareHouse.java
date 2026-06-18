package com.example.lld.inventoryManagement.dto;

public class WareHouse {
    private String wareHouseId;
    private String wareHouseName;
    private NewLocation location;
    private Stock[] stocks;

    public WareHouse(String wareHouseId, String wareHouseName, NewLocation location, Stock[] stocks) {
        this.wareHouseId = wareHouseId;
        this.wareHouseName = wareHouseName;
        this.location = location;
        this.stocks = stocks;
    }

    public String getWareHouseId() {
        return wareHouseId;
    }

    public void setWareHouseId(String wareHouseId) {
        this.wareHouseId = wareHouseId;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public NewLocation getLocation() {
        return location;
    }

    public void setLocation(NewLocation location) {
        this.location = location;
    }

    public Stock[] getStocks() {
        return stocks;
    }

    public void setStocks(Stock[] stocks) {
        this.stocks = stocks;
    }

}
