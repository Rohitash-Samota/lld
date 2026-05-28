package com.example.lld.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import com.example.lld.dto.parking.Ticket;
import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.TicketStatus;

public class TicketService {
    private Map<String, Ticket> tickets = new HashMap<>();

    public Ticket createTicket(Vehicle vehicle) {

        String ticketNumber = generateTicketId(vehicle);

        Ticket ticket = new Ticket(
                ticketNumber,
                vehicle,
                LocalDateTime.now(ZoneId.of("Asia/Kolkata")),
                TicketStatus.ACTIVE);
        tickets.put(ticketNumber, ticket);
        return ticket;
    }

    public Ticket getTicket(String ticketNumber) {
        return tickets.get(ticketNumber);
    }

    private String generateTicketId(Vehicle vehicle) {
        return "TICKET-" + vehicle.getRCNumber()
                + "-" + System.currentTimeMillis();
    }
}
