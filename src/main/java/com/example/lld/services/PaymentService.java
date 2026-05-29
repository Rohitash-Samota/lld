package com.example.lld.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.dto.parking.Payment;
import com.example.lld.dto.parking.Ticket;
import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.PaymentMethod;
import com.example.lld.enums.parking.SpotType;
import com.example.lld.factory.parking.PricingStrategyFactory;
import com.example.lld.strategy.parking.PricingStrategy;

@Service
public class PaymentService {

    // here create a payment and
    private final Map<String, Payment> payments = new HashMap<>();

    public boolean makePayment(Ticket ticket, Vehicle vehicle, PaymentMethod paymentMethod) {

        LocalDateTime exitTime = LocalDateTime.now();
        long hours = Duration.between(ticket.getIssueTime(), exitTime).toHours();

        if (hours == 0) {
            hours = 1;
        }

        SpotType spotType = vehicle.getSpotNeeded();

        PricingStrategy pricingStrategy = PricingStrategyFactory.getPricingStrategy(spotType);
        double amount = pricingStrategy.calculate(hours);
        Payment payment = new Payment();
        payment.makePayment(ticket.getTicketNumber(), amount, paymentMethod);

        payments.put(payment.getPaymentId(), payment);

        return true;
    }

    // todo refund based on ticket no
    public boolean paymentRefunded(Ticket ticket) {
        return true;
    }

    public Map<String, Payment> getAllPayments() {
        return payments;
    }

    public Map<String, Payment> getAllRefundPayments() {

        if (payments == null || payments.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<String, Payment> allRefundedPayments = new HashMap<>();

        payments.forEach((paymentId, payment) -> {
            if (Payment.isRefundedPayment(payment)) {
                allRefundedPayments.put(paymentId, payment);
            }
        });

        return allRefundedPayments;
    }
}
