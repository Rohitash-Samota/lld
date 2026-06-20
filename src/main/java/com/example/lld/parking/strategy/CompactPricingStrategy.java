package com.example.lld.parking.strategy;

public class CompactPricingStrategy implements PricingStrategy {

    @Override
    public double getFixedFirstHourRate() {
        return 20;
    }

    public double getHourlyRate() {
        return 10;
    }

    public double getDailyRate() {
        return 50;
    }

    public double getMonthlyRate() {
        return 250;
    }
}
