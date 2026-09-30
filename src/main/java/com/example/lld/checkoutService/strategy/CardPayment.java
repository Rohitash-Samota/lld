package com.example.lld.checkoutService.strategy;

import org.springframework.stereotype.Service;

import com.example.lld.checkoutService.PaymentStrategy;
import com.example.lld.checkoutService.dto.PaymentRequest;
import com.example.lld.checkoutService.dto.PaymentResult;

@Service("cardPayment")
final public class CardPayment implements PaymentStrategy {
    @Override
    public PaymentResult pay(PaymentRequest request) {
        return new PaymentResult(null, null, 0);
    }
}
