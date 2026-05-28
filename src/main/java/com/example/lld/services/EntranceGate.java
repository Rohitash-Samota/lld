package com.example.lld.services;

import org.springframework.stereotype.Service;

import com.example.lld.dto.parking.Ticket;
import com.example.lld.dto.parking.Vehicle;

@Service
public class EntranceGate {
    private TicketService ticketService;
    private ParkingService parkingService;

    public EntranceGate(TicketService ticketService, ParkingService parkingService) {
        this.ticketService = ticketService;
        this.parkingService = parkingService;
    }

    public String parkVehicle(Vehicle vehicle) {
        Ticket ticket = ticketService.createTicket(vehicle);
        parkingService.parkVehicle(vehicle);
        return ticket.getTicketNumber();
    }
}
