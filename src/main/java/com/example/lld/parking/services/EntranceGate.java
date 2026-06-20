package com.example.lld.parking.services;

import org.springframework.stereotype.Service;

import com.example.lld.parking.dto.Vehicle;

@Service
public class EntranceGate {

    private final TicketService ticketService;
    private final ParkingService parkingService;

    public EntranceGate(TicketService ticketService, ParkingService parkingService) {
        this.ticketService = ticketService;
        this.parkingService = parkingService;
    }

    public String parkVehicle(Vehicle vehicle) {
        String ticketId = ticketService.createTicket(vehicle);
        parkingService.parkVehicle(vehicle, ticketId);
        return ticketId;
    }
}
