package com.example.lld.booking.interfaces;

import java.util.List;

import com.example.lld.booking.dto.Ticket;

public interface BookingRepository {
    void save(Ticket ticket);

    List<Ticket> findAll();
}
