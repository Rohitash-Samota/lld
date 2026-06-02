package com.example.lld.services.booking;

import java.util.List;

import com.example.lld.dto.booking.Ticket;

public interface BookingRepository {
    void save(Ticket ticket);

    List<Ticket> findAll();
}
