package com.example.lld.checkoutService;

import org.springframework.stereotype.Service;

import com.example.lld.checkoutService.dto.PaymentRequest;
import com.example.lld.checkoutService.dto.PaymentResult;

@Service("paymentStrategy")
public interface PaymentStrategy {
    PaymentResult pay(PaymentRequest request);
}
