package com.example.lld.payment.dto;

import java.util.UUID;

import com.example.lld.parking.enums.PaymentMethod;
import com.example.lld.parking.enums.PaymentStatus;
import com.example.lld.payment.enums.PGType;

public class Payment {
    private final String paymentId;
    private String orderId;
    private double amount;
    private PaymentStatus paymentStatus;
    private PaymentMethod paymentMethod;
    private PGType pgType;

    public Payment(double amount, String orderId, PaymentMethod paymentMethod, PaymentStatus paymentStatus,
            PGType pgType) {
        this.paymentId = "PAY_" + UUID.randomUUID().toString();
        this.amount = amount;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.pgType = pgType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPayemntMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PGType getPgType() {
        return pgType;
    }

    public void setPgType(PGType pgType) {
        this.pgType = pgType;
    }

}
