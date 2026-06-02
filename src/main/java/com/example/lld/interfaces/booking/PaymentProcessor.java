package com.example.lld.interfaces.booking;

import com.example.lld.dto.booking.Ticket;

public interface PaymentProcessor {
    boolean processPayment(Ticket ticket, double amount);
}
