package com.example.lld.checkoutService;

import com.example.lld.checkoutService.dto.PaymentRequest;
import com.example.lld.checkoutService.dto.PaymentResult;

public final class PaymentContext {

    public PaymentResult pay(PaymentStrategy paymentStrategy, PaymentRequest paymentRequest) {
        if (paymentStrategy == null) {
            throw new IllegalArgumentException("Payment strategy cannot be null");
        }
        return paymentStrategy.pay(paymentRequest);
    }

    public PaymentResult payment(PaymentStrategy paymentStrategy, PaymentRequest paymentRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'payment'");
    }
}