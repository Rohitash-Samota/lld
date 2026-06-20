package com.example.lld.parking.strategy;

public class RegularPricingStrategy implements PricingStrategy {

    @Override
    public double getFixedFirstHourRate() {
        return 30;
    }

    public double getHourlyRate() {
        return 20;
    }

    public double getDailyRate() {
        return 60;
    }

    public double getMonthlyRate() {
        return 350;
    }
}
