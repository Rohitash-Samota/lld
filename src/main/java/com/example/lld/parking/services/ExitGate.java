package com.example.lld.parking.services;

import com.example.lld.parking.dto.ParkingSpot;
import com.example.lld.parking.dto.Ticket;
import com.example.lld.parking.dto.Vehicle;
import com.example.lld.parking.enums.PaymentMethod;

public class ExitGate {

    private PaymentService paymentService;
    private ParkingService parkingService;
    private TicketService ticketService;
    private VehicleService vehicleService;

    public ExitGate(
            PaymentService paymentService,
            ParkingService parkingService,
            TicketService ticketService,
            VehicleService vehicleService) {

        this.paymentService = paymentService;
        this.parkingService = parkingService;
        this.ticketService = ticketService;
        this.vehicleService = vehicleService;
    }

    public boolean unparkVehicle(String ticketNumber) {

        Ticket ticket = ticketService.getTicket(ticketNumber);

        if (ticket == null) {
            System.out.println("Invalid Ticket");
            return false;
        }

        String vehicleNumber = ticket.getVehicleNumber();
        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber);
        if (vehicle == null) {
            System.err.println("Vehicle Not Found");
            return false;

        }
        boolean paymentDone = paymentService.makePayment(ticket, vehicle, PaymentMethod.CASH);

        if (!paymentDone) {
            return false;
        }

        ParkingSpot parkingSpot = parkingService.getParkingSpot(ticketNumber);
        System.err.println("Parking Spot in Exitgate" + parkingSpot);
        return parkingService.unparkVehicle(parkingSpot);
    }
}
