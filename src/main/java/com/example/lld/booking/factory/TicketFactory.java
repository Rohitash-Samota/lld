package com.example.lld.booking.factory;

import com.example.lld.booking.dto.Seat;
import com.example.lld.booking.dto.Show;
import com.example.lld.booking.dto.Ticket;

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