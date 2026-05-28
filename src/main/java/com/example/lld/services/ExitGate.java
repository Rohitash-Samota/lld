package com.example.lld.services;

import com.example.lld.dto.parking.Ticket;
import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.PaymentMethod;

public class ExitGate {

    private PaymentService paymentService;
    private ParkingService parkingService;
    private TicketService ticketService;

    public ExitGate(PaymentService paymentService, ParkingService parkingService) {
        this.paymentService = paymentService;
        this.parkingService = parkingService;
    }

    public boolean unparkVehicle(String ticketNumber) {
        Ticket ticket = ticketService.getTicket(ticketNumber);
        Vehicle vehicle = ticket.getVehicle();
        boolean isPayment = paymentService.makePayment(ticket, vehicle, PaymentMethod.CASH);
        if (isPayment) {
            boolean isUnPark = parkingService.unparkVehicle(null);
        }
        return false;
    }
}
