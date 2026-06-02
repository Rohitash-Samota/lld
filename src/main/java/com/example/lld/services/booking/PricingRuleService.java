package com.example.lld.services.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lld.dto.booking.PricingRule;
import com.example.lld.dto.booking.Seat;
import com.example.lld.dto.booking.Show;
import com.example.lld.enums.booking.ScreenType;
import com.example.lld.enums.booking.SeatType;

@Service
public class PricingRuleService {

    private final List<PricingRule> pricingRules = new ArrayList<>();

    public PricingRule addPricingRule(String movieId, String theaterId,
            ScreenType screenType,
            SeatType seatType,
            double amount, double weekendSurcharge,
            double festivalSurcharge) {

        PricingRule rule = new PricingRule(movieId, theaterId, screenType,
                seatType, amount, weekendSurcharge, festivalSurcharge);
        pricingRules.add(rule);
        return rule;
    }

    public Optional<PricingRule> findMatchingRule(Show show, Seat seat) {
        return pricingRules.stream()
                .filter(rule -> rule.matches(show, seat))
                .findFirst();
    }
}
