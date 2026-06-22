package com.example.lld.payment.interfaces;

import com.example.lld.parking.enums.PaymentStatus;
import com.example.lld.payment.dto.PaymentRequest;
import com.example.lld.payment.dto.PaymentResponse;


public interface PaymentI {
    PaymentResponse initiatePayment(PaymentRequest paymentRequest);
    PaymentResponse retryPayment(RetryPaymentRequest retryPaymentRequest);
    PaymentStatus getPaymentStatus(String paymentId);
}
