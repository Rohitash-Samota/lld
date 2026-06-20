package com.example.lld.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.PricingRule;
import com.example.lld.booking.dto.Seat;
import com.example.lld.booking.dto.Show;

@Service
public class PricingService {

    @Autowired
    private PricingRuleService pricingRuleService;

    public double calculateTicketPrice(Show show, Seat seat, boolean weekend, boolean festival) {

        double basePrice = show.getMovie() != null ? show.getMovie().getBasePrice() : 0.0;
        double amount = basePrice;

        PricingRule rule = pricingRuleService.findMatchingRule(show, seat).orElse(null);
        if (rule != null) {
            amount += rule.getAmount();
            if (weekend) {
                amount += rule.getWeekendSurcharge();
            }
            if (festival) {
                amount += rule.getFestivalSurcharge();
            }
        }

        return amount;
    }
}
