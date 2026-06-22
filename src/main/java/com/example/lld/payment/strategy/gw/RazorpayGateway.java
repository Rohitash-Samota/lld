package com.example.lld.payment.strategy.gw;

import com.example.lld.payment.dto.Payment;
import com.example.lld.payment.interfaces.PaymentGatewayI;

public class RazorpayGateway implements PaymentGatewayI {

    @Override
    public boolean processPayment(Payment payment) {
        System.out.println("Processing via Razorpay");
        return true;
    }

    @Override
    public boolean refund(Payment payment) {
        System.out.println("Refund via Razorpay");
        return true;
    }

    @Override
    public String getGatewayName() {
        return "RAZORPAY";
    }
}