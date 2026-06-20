package com.example.lld.inventoryManagement.services;

import com.example.lld.inventoryManagement.dto.Stock;

public class AlertService {
    public void lowStockAlert(Stock s) {
        System.out.println("[ALERT] Low stock: " + s.getStockName() + " (id=" + s.getStockId() + ") qty="
                + s.getProductQuantity());
    }
}
