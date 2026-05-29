package com.example.lld;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.SpotType;
import com.example.lld.enums.parking.VehicleType;
import com.example.lld.services.DashboardService;
import com.example.lld.services.EntranceGate;
import com.example.lld.services.ExitGate;
import com.example.lld.services.ParkingService;
import com.example.lld.services.PaymentService;
import com.example.lld.services.TicketService;
import com.example.lld.services.VehicleService;

@Component
public class TestRunnerParking implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        ParkingService parkingService = new ParkingService();
        TicketService ticketService = new TicketService();
        PaymentService paymentService = new PaymentService();
        EntranceGate entranceGate = new EntranceGate(ticketService, parkingService);
        VehicleService vehicleService = new VehicleService();
        ExitGate exitGate = new ExitGate(paymentService, parkingService, ticketService, vehicleService);
        DashboardService dashboardService = new DashboardService(parkingService, paymentService);

        Vehicle vehicle1 = vehicleService.createVehicle("RJ23ABCD1666", VehicleType.CAR, SpotType.REGULAR);

        String ticket1 = entranceGate.parkVehicle(vehicle1);

        System.out.println("\nVehicle 1 Ticket => " + ticket1);

        Vehicle vehicle2 = vehicleService.createVehicle("RJ23BCCD1666", VehicleType.CAR, SpotType.LARGE);

        String ticket2 = entranceGate.parkVehicle(vehicle2);

        System.out.println("Vehicle 2 Ticket => " + ticket2);
        System.out.println("\n===== DASHBOARD AFTER PARKING =====");
        dashboardService.showDetailedDashboard();

        System.out.println("\n===== UNPARK VEHICLE 1 =====");
        boolean isUnparked = exitGate.unparkVehicle(ticket1);

        System.out.println("Unpark Status => " + isUnparked);

        System.out.println("\n===== DASHBOARD AFTER UNPARKING =====");
        dashboardService.showDetailedDashboard();

        System.out.println("\n===== DASHBOARD Payments =====");
        dashboardService.showPaymentsDetail();
    }
}
