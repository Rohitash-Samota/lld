package com.example.lld.inventoryManagement.dto;

import java.lang.reflect.AccessFlag.Location;

public class WareHouse {
    private String wareHouseId;
    private String wareHouseName;
    private Location location;
    private Stock[] stocks;

    public WareHouse(String wareHouseId, String wareHouseName, Location location, Stock[] stocks) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Stock[] getStocks() {
        return stocks;
    }

    public void setStocks(Stock[] stocks) {
        this.stocks = stocks;
    }

}
