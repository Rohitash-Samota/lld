package com.example.lld.parking.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.parking.dto.Level;
import com.example.lld.parking.dto.ParkingSpot;
import com.example.lld.parking.dto.Payment;

@Service
public class DashboardService {

    private final ParkingService parkingService;
    private final PaymentService paymentService;

    public DashboardService(ParkingService parkingService, PaymentService paymentService) {
        this.parkingService = parkingService;
        this.paymentService = paymentService;
    }

    public void showDashboard() {

        Level[] levels = parkingService.getAllLevels();

        System.out.println("\n========== PARKING DASHBOARD ==========");

        for (Level level : levels) {

            System.out.println("--------------------------------------");
            System.out.println("Level          : " + level.getLevel());
            System.out.println("Total Spots    : " + level.getTotalSpots());
            System.out.println("Available      : " + level.getAvailableSpotsCount());
            System.out.println("Occupied       : "
                    + (level.getTotalSpots() - level.getAvailableSpotsCount()));
        }

        System.out.println("======================================\n");
    }

    public void showDetailedDashboard() {

        Level[] levels = parkingService.getAllLevels();

        for (Level level : levels) {

            System.out.println("\n========== LEVEL " + level.getLevel() + " ==========");

            System.out.printf(
                    "%-10s %-12s %-12s %-18s %-20s%n",
                    "Spot",
                    "Type",
                    "Status",
                    "Vehicle",
                    "Ticket Number");

            for (ParkingSpot spot : level.getSlots()) {

                System.out.printf(
                        "%-10s %-12s %-12s %-18s %-20s%n",
                        spot.getSpotId(),
                        spot.getSpotType(),
                        spot.getStatus(),
                        spot.getRcNumber() == null ? "EMPTY" : spot.getRcNumber(),
                        spot.getTicketNumber() == null ? "N/A" : spot.getTicketNumber()
                );
            }
        }
    }

    public void showPaymentsDetail() {

        Map<String, Payment> payments = paymentService.getAllPayments();

        payments.forEach((paymentId, payment) -> {

            System.out.println("Payment ID : " + paymentId);
            System.out.println("Amount     : " + payment.getAmount());
            System.out.println("Method     : " + payment.getPaymentMethod());
            System.out.println("Status     : " + payment.getStatus());
            System.out.println("---------------------------");

        });
    }
}
