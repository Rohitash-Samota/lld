package com.example.lld.dto.parking;

import java.time.LocalDateTime;
import java.time.ZoneId;

import com.example.lld.enums.parking.PaymentMethod;
import com.example.lld.enums.parking.PaymentStatus;

public class Payment {

    private String paymentId;
    private String ticketId;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime paymentTime;

    public Payment makePayment(String ticketId, double amount, PaymentMethod paymentMethod) {
        this.setPaymentId(createPaymentId(ticketId, paymentMethod));
        this.setTicketId(ticketId);
        this.setAmount(amount);
        this.setPaymentMethod(paymentMethod);
        this.setStatus(PaymentStatus.COMPLETED);
        this.setPaymentTime();
        return this;
    }

    public boolean paymentRefund(Payment payment) {
        payment.setStatus(PaymentStatus.REFUNDED);
        return true;
    }

    public static boolean isRefundedPayment(Payment payment) {
        if (payment.getStatus() == PaymentStatus.REFUNDED) {
            return true;
        }
        return false;
    }

    private String createPaymentId(String ticketId, PaymentMethod paymentMethod) {
        return "PY-" + paymentMethod + '-' + ticketId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime() {
        this.paymentTime = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

}
