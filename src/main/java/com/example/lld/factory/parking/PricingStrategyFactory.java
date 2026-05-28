package com.example.lld.factory.parking;

import com.example.lld.enums.parking.SpotType;
import com.example.lld.strategy.parking.*;

public class PricingStrategyFactory {
    public static PricingStrategy getPricingStrategy(SpotType spotType) {
        switch (spotType) {
            case COMPACT:
                return new CompactPricingStrategy();
            case REGULAR:
                return new RegularPricingStrategy();
            case LARGE:
                return new LargePricingStrategy();
            case EXTRA_LARGE:
                return new ExLargePricingStrategy();
            case LUXURY:
                return new LuxPricingStrategy();
            default:
                return new CompactPricingStrategy();
        }
    }
}
