package com.example.lld.checkoutService.dto;

import com.example.lld.checkoutService.enums.PaymentMethod;

public class PaymentRequest {
    private double amount;
    private PaymentMethod paymentMethod;

    public PaymentRequest(double amount, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
