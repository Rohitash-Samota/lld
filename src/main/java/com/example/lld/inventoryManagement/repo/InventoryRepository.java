package com.example.lld.inventoryManagement.repo;

import com.example.lld.inventoryManagement.dto.Stock;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryRepository {
    private final Map<String, Stock> stocks = new ConcurrentHashMap<>();

    public void addStock(Stock stock) {
        stocks.put(stock.getStockId(), stock);
    }

    public Stock getStock(String stockId) {
        return stocks.get(stockId);
    }

    public Collection<Stock> listAll() {
        return Collections.unmodifiableCollection(stocks.values());
    }

    public void updateQuantity(String stockId, int newQuantity) {
        Stock s = stocks.get(stockId);
        if (s != null) {
            s.setProductQuantity(newQuantity);
        }
    }

}
