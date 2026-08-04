package com.example.lld.checkoutService;

import java.util.UUID;

import com.example.lld.checkoutService.enums.PaymentMethod;

public class PaymentModel {
    private String paymentId;
    private PaymentMethod paymentMethod;
    private double amount;
    private String message;

    public PaymentModel(PaymentMethod paymentMethod, double amount, String message) {
        this.paymentId = UUID.randomUUID().toString();
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.message = message;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
