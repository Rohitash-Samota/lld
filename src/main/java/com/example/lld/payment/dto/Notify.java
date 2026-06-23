package com.example.lld.payment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.example.lld.payment.enums.NotifyType;

public class Notify {

    private final String notifyId;
    private String paymentId;
    private String message;
    private NotifyType notifyType;
    private LocalDateTime createdAt;

    public Notify(String paymentId,
            String message,
            NotifyType notifyType) {

        this.notifyId = "NOTIFY_" + UUID.randomUUID();
        this.paymentId = paymentId;
        this.message = message;
        this.notifyType = notifyType;
        this.createdAt = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public NotifyType getNotifyType() {
        return notifyType;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}