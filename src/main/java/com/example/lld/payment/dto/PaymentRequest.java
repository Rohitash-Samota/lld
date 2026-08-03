package com.example.lld.payment.dto;

import com.example.lld.payment.enums.PGType;

public class PaymentRequest {
    private String orderId;
    private double amount;
    private PGType pgt;

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

    public void setPgt(PGType pgt) {
        this.pgt = pgt;
    }

    public PGType getPgt() {
        return pgt;
    }
}
