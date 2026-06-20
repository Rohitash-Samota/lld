package com.example.lld.booking.interfaces;

import com.example.lld.booking.dto.Ticket;

public interface PaymentProcessor {
    boolean processPayment(Ticket ticket, double amount);
}
