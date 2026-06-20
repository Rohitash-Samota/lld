package com.example.lld.parking.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.parking.dto.Ticket;
import com.example.lld.parking.dto.Vehicle;

@Service
public class TicketService {

    private final Map<String, Ticket> tickets = new HashMap<>();

    public TicketService() {

    }

    public String createTicket(Vehicle vehicle) {
        String vehicleRCNumber = vehicle.getRCNumber();
        Ticket ticket = new Ticket(vehicleRCNumber);

        tickets.put(ticket.getTicketNumber(), ticket);
        return ticket.getTicketNumber();
    }

    public Ticket getTicket(String ticketNumber) {
        return tickets.get(ticketNumber);
    }
}
