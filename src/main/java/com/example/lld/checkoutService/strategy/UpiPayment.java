package com.example.lld.checkoutService.strategy;

import org.springframework.stereotype.Service;

import com.example.lld.checkoutService.PaymentStrategy;
import com.example.lld.checkoutService.dto.PaymentRequest;
import com.example.lld.checkoutService.dto.PaymentResult;

@Service
final public class UpiPayment implements PaymentStrategy {
    @Override
    public PaymentResult pay(PaymentRequest request) {
        return new PaymentResult(null, null, 0);
    }
}
