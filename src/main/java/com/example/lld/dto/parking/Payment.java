package com.example.lld.dto.parking;

import java.time.LocalDateTime;

import com.example.lld.enums.parking.PaymentMethod;
import com.example.lld.enums.parking.PaymentStatus;

public class Payment {

    private String paymentId;
    private Ticket ticket;
    private double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private LocalDateTime paymentTime;

}
