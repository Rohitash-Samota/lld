package com.example.lld.inventoryManagement.services;

import com.example.lld.inventoryManagement.dto.Stock;

public class OrderService {
    private final InventoryService inventoryService;

    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public boolean placeOrder(String stockId, int qty) {
        Stock s = inventoryService.getStock(stockId);
        if (s == null) {
            System.out.println("Order failed: stock not found " + stockId);
            return false;
        }
        boolean ok = inventoryService.decreaseStock(stockId, qty);
        if (!ok) {
            System.out.println("Order failed: insufficient stock for " + stockId);
            return false;
        }
        System.out.println("Order placed: " + qty + " x " + s.getStockName());
        return true;
    }
}
