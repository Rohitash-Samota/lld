package com.example.lld.checkoutService;

import java.util.HashMap;

import com.example.lld.checkoutService.dto.PaymentRequest;
import com.example.lld.checkoutService.dto.PaymentResult;

final public class CheckoutService {
    private HashMap<String, PaymentModel> paymentsList = new HashMap<>();

    private final PaymentStrategyFactory paymentStrategyFactory;
    private final PaymentContext paymentContext;

    public CheckoutService(PaymentStrategyFactory paymentStrategyFactory, PaymentContext paymentContext) {
        this.paymentContext = paymentContext;
        this.paymentStrategyFactory = paymentStrategyFactory;
    }

    public String payment(PaymentRequest paymentRequest) {
        PaymentStrategy paymentStrategy = paymentStrategyFactory.getPaymentStrategy(paymentRequest.getPaymentMethod());
        PaymentResult paymentResult = paymentContext.payment(paymentStrategy, paymentRequest);
        return "";
    }
}
