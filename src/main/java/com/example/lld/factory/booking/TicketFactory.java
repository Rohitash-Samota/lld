package com.example.lld.factory.booking;

import com.example.lld.dto.booking.Seat;
import com.example.lld.dto.booking.Show;
import com.example.lld.dto.booking.Ticket;

public class TicketFactory {

    public static Ticket createTicket(Show show, Seat seat, double amount) {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber("TKT-" + show.getShowId() + "-" + seat.getRow() + seat.getColNumber());
        ticket.setShow(show);
        ticket.setSeat(seat);
        ticket.setAmount(amount);
        return ticket;
    }
}