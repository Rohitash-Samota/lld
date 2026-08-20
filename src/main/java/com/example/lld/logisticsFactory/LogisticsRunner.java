package com.example.lld.logisticsfactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogisticsRunner implements CommandLineRunner {

    private final LogisticsService logisticsService;

    public LogisticsRunner(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }

    @Override
    public void run(String... args) {

        Product car = new Product("Cars-1", "100 cars");
        Product bike = new Product("Bike-2", "Bike");

        logisticsService.processDelivery(car, TransPortType.ROAD);
        logisticsService.processDelivery(bike, TransPortType.SEA);
    }
}