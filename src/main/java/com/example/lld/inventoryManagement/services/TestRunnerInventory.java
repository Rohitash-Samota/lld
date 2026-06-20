package com.example.lld.inventoryManagement.services;

import com.example.lld.inventoryManagement.dto.Stock;
import com.example.lld.inventoryManagement.enums.ProductType;
import com.example.lld.inventoryManagement.repo.InventoryRepository;

public class TestRunnerInventory {
    public static void main(String[] args) {
        InventoryRepository repo = new InventoryRepository();
        AlertService alerts = new AlertService();
        InventoryService inventory = new InventoryService(repo, alerts);
        OrderService orders = new OrderService(inventory);

        Stock s1 = new Stock("sku-100", "Widget A", ProductType.P0, 10);
        Stock s2 = new Stock("sku-200", "Widget B", ProductType.P1, 3);

        inventory.addStock(s1);
        inventory.addStock(s2);

        System.out.println("Listing all stocks:");
        inventory.listAll().forEach(st -> System.out
                .println(st.getStockId() + ": " + st.getStockName() + " -> " + st.getProductQuantity()));

        System.out.println("Placing order for 2 x Widget A");
        orders.placeOrder("sku-100", 2);

        System.out.println("Placing order for 2 x Widget B");
        orders.placeOrder("sku-200", 2);

        System.out.println("Placing order for 2 x Widget B (should trigger low stock)");
        orders.placeOrder("sku-200", 2);
    }
}
