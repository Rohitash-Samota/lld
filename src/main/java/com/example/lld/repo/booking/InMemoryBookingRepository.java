package com.example.lld.repo.booking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.lld.dto.booking.Ticket;
import com.example.lld.interfaces.booking.BookingRepository;

@Repository
public class InMemoryBookingRepository implements BookingRepository {

    private final List<Ticket> bookings = new ArrayList<>();

    @Override
    public void save(Ticket ticket) {
        bookings.add(ticket);
    }

    @Override
    public List<Ticket> findAll() {
        return Collections.unmodifiableList(bookings);
    }
}
