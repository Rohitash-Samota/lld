package com.example.lld.parking.strategy;

public class LargePricingStrategy implements PricingStrategy {

    @Override
    public double getFixedFirstHourRate() {
        return 40;
    }

    public double getHourlyRate() {
        return 30;
    }

    public double getDailyRate() {
        return 80;
    }

    public double getMonthlyRate() {
        return 450;
    }
}
