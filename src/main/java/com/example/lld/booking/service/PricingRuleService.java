package com.example.lld.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.PricingRule;
import com.example.lld.booking.dto.Seat;
import com.example.lld.booking.dto.Show;
import com.example.lld.booking.enums.ScreenType;
import com.example.lld.booking.enums.SeatType;

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
