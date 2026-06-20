package com.example.lld.parking.strategy;

public class LuxPricingStrategy implements PricingStrategy {

    @Override
    public double getFixedFirstHourRate() {
        return 100;
    }

    public double getHourlyRate() {
        return 80;
    }

    public double getDailyRate() {
        return 200;
    }

    public double getMonthlyRate() {
        return 1500;
    }
}
