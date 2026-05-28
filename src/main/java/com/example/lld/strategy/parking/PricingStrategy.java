package com.example.lld.strategy.parking;

public interface PricingStrategy {
    double getFixedFirstHourRate();

    double getHourlyRate();

    double getDailyRate();

    double getMonthlyRate();

    default double calculate(long hours) {
        if (hours < 2) {
            return getFixedFirstHourRate();
        }

        return getFixedFirstHourRate() + (hours - 2) * getHourlyRate();
    }
}
