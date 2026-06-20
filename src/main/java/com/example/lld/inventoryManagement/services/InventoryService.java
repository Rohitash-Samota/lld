package com.example.lld.inventoryManagement.services;

import com.example.lld.inventoryManagement.dto.Stock;
import com.example.lld.inventoryManagement.repo.InventoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryService {
    private final InventoryRepository repository;
    private final AlertService alertService;
    private int lowStockThreshold = 5;

    public InventoryService(InventoryRepository repository, AlertService alertService) {
        this.repository = repository;
        this.alertService = alertService;
    }

    public void addStock(Stock stock) {
        repository.addStock(stock);
        checkAndAlert(stock);
    }

    public Stock getStock(String stockId) {
        return repository.getStock(stockId);
    }

    public List<Stock> listAll() {
        return repository.listAll().stream().collect(Collectors.toList());
    }

    public boolean decreaseStock(String stockId, int qty) {
        Stock s = repository.getStock(stockId);
        if (s == null)
            return false;
        int available = s.getProductQuantity();
        if (available < qty)
            return false;
        s.setProductQuantity(available - qty);
        checkAndAlert(s);
        return true;
    }

    public void increaseStock(String stockId, int qty) {
        Stock s = repository.getStock(stockId);
        if (s == null)
            return;
        s.setProductQuantity(s.getProductQuantity() + qty);
        checkAndAlert(s);
    }

    private void checkAndAlert(Stock s) {
        if (s.getProductQuantity() <= lowStockThreshold) {
            alertService.lowStockAlert(s);
        }
    }

    public void setLowStockThreshold(int threshold) {
        this.lowStockThreshold = threshold;
    }

}
