package com.example.lld.factory.parking;

import com.example.lld.enums.parking.SpotType;
import com.example.lld.strategy.parking.CompactPricingStrategy;
import com.example.lld.strategy.parking.ExLargePricingStrategy;
import com.example.lld.strategy.parking.LargePricingStrategy;
import com.example.lld.strategy.parking.LuxPricingStrategy;
import com.example.lld.strategy.parking.PricingStrategy;
import com.example.lld.strategy.parking.RegularPricingStrategy;

/**
 * Factory Design Pattern
 *
 * This factory is responsible for creating and returning the
 * appropriate PricingStrategy implementation based on the SpotType.
 *
 * Benefits:
 * - Encapsulates object creation logic.
 * - Reduces coupling between client code and concrete strategy classes.
 * - Makes it easier to add new pricing strategies in the future.
 */
public class PricingStrategyFactory {

    /**
     * Returns the pricing strategy corresponding to the given parking spot type.
     *
     * @param spotType Type of parking spot
     * @return Appropriate PricingStrategy implementation
     */
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
                // Fallback strategy if no matching spot type is found
                return new CompactPricingStrategy();
        }
    }
}