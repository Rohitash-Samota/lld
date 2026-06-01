package com.example.lld.services.booking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.lld.dto.booking.Ticket;
import com.example.lld.services.parking.PaymentService;

public class BookingService {
    private final List<Ticket> bookings = new ArrayList<>();

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ShowService showService;

}
