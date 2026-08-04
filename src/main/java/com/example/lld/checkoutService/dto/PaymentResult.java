package com.example.lld.checkoutService.dto;

public class PaymentResult {
    private String paymentId;
    private String message;
    private double amount;

    public PaymentResult(String paymentId, String message, double amount) {
        this.paymentId = paymentId;
        this.message = message;
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

}
