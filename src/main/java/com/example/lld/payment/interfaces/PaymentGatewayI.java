package com.example.lld.payment.interfaces;

import com.example.lld.payment.dto.Payment;

public interface PaymentGatewayI {

    boolean processPayment(Payment payment);

    boolean refund(Payment payment);

    String getGatewayName();
}