package com.example.lld.strategy.parking;

public class ExLargePricingStrategy implements PricingStrategy {
    @Override
    public double getFixedFirstHourRate() {
        return 50;
    }

    public double getHourlyRate() {
        return 40;
    }

    public double getDailyRate() {
        return 100;
    }

    public double getMonthlyRate() {
        return 550;
    }
}
