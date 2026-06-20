package com.example.lld.booking.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.booking.interfaces.PaymentProcessor;
import com.example.lld.parking.dto.Payment;

@Service
public class PaymentService implements PaymentProcessor {
    private final Map<String, Payment> payments = new HashMap<>();

    @Override
    public boolean processPayment(com.example.lld.booking.dto.Ticket ticket, double amount) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentId("PAY-" + ticket.getTicketNumber());
        payments.put(payment.getPaymentId(), payment);
        return true;
    }

    public Map<String, Payment> getAllPayments() {
        return payments;
    }
}
