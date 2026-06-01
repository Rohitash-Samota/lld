package com.example.lld.services.booking;

import java.util.HashMap;
import java.util.Map;

import com.example.lld.dto.parking.Payment;

public class PaymentService {
    private final Map<String, Payment> payments = new HashMap<>();

    public Payment makePayment() {
        return new Payment();
    }

    public Map<String, Payment> getAllPayments() {
        return payments;
    }
}
