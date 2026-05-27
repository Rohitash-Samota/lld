package com.example.lld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.lld.dto.parking.*;
import com.example.lld.enums.parking.*;
import com.example.lld.services.*;

public class ParkingLotSystemTest {

    private ParkingLot parkingLot;

    @BeforeEach
    public void setUp() {
        // Initialize parking lot with 3 levels, 10 spots per level (30 total spots)
        parkingLot = new ParkingLot("TEST-PL-001", "123 Test Street", 3, 10);
    }

    @Test
    public void testParkingLotInitialization() {
        assertNotNull(parkingLot);
        assertNotNull(parkingLot.getParkingLotService());
        assertEquals("TEST-PL-001", parkingLot.getParkingLotId());
        assertEquals(30, parkingLot.getParkingLotService().getTotalParkingSpots());
        assertEquals(30, parkingLot.getParkingLotService().getTotalAvailableSpots());
    }

    @Test
    public void testParkVehicle_Success() {
        Vehicle car = new Vehicle("CAR001", SpotType.COMPACT, VehicleType.CAR);
        Ticket ticket = parkingLot.parkVehicle(car);

        assertNotNull(ticket);
        assertNotNull(ticket.getTicketNumber());
        assertEquals(VehicleType.CAR, ticket.getVehicle().getVehicleType());
        assertEquals(29, parkingLot.getParkingLotService().getTotalAvailableSpots());
    }

    @Test
    public void testParkMultipleVehicles() {
        Vehicle car1 = new Vehicle("CAR001", SpotType.COMPACT, VehicleType.CAR);
        Vehicle bike1 = new Vehicle("BIKE001", SpotType.REGULAR, VehicleType.BIKE);
        Vehicle bus1 = new Vehicle("BUS001", SpotType.EXTRA_LARGE, VehicleType.BUS);

        Ticket ticket1 = parkingLot.parkVehicle(car1);
        Ticket ticket2 = parkingLot.parkVehicle(bike1);
        Ticket ticket3 = parkingLot.parkVehicle(bus1);

        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertNotNull(ticket3);
        assertEquals(27, parkingLot.getParkingLotService().getTotalAvailableSpots());
    }

    @Test
    public void testExitVehicle_Success() {
        Vehicle car = new Vehicle("CAR002", SpotType.COMPACT, VehicleType.CAR);
        Ticket ticket = parkingLot.parkVehicle(car);
        assertEquals(29, parkingLot.getParkingLotService().getTotalAvailableSpots());

        Payment payment = parkingLot.exitVehicle(ticket.getTicketNumber());

        assertNotNull(payment);
        assertNotNull(payment.getPaymentId());
        assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
        assertEquals(30, parkingLot.getParkingLotService().getTotalAvailableSpots());
    }

    @Test
    public void testExitVehicle_InvalidTicket() {
        Vehicle car = new Vehicle("CAR003", SpotType.COMPACT, VehicleType.CAR);
        parkingLot.parkVehicle(car);
        
        assertThrows(RuntimeException.class, () -> {
            parkingLot.exitVehicle("INVALID-TICKET-12345");
        });
    }

    @Test
    public void testDisplayBoard() {
        assertNotNull(parkingLot.getDisplayBoard());
        parkingLot.displayBoard();
    }

    @Test
    public void testParkingReport() {
        Vehicle car = new Vehicle("CAR005", SpotType.LARGE, VehicleType.CAR);
        parkingLot.parkVehicle(car);

        assertDoesNotThrow(() -> {
            parkingLot.generateParkingReport();
        });
    }

    @Test
    public void testRevenueReport() {
        Vehicle car = new Vehicle("CAR006", SpotType.LARGE, VehicleType.CAR);
        Ticket ticket = parkingLot.parkVehicle(car);

        parkingLot.exitVehicle(ticket.getTicketNumber());

        assertDoesNotThrow(() -> {
            parkingLot.generateRevenueReport();
        });
    }

    @Test
    public void testMultipleLevelsManagement() {
        parkingLotService service = parkingLot.getParkingLotService();
        Level[] levels = service.getLevels();

        assertEquals(3, levels.length);
        for (Level level : levels) {
            assertNotNull(level);
            assertEquals(10, level.getTotalSpots());
        }
    }

    @Test
    public void testMultipleParking() {
        SpotType[] spotTypes = {SpotType.COMPACT, SpotType.REGULAR, SpotType.LARGE, SpotType.EXTRA_LARGE};
        
        for (int i = 0; i < 8; i++) {
            SpotType spotType = spotTypes[i % spotTypes.length];
            Vehicle vehicle = new Vehicle("VEH-" + i, spotType, VehicleType.CAR);
            Ticket ticket = parkingLot.parkVehicle(vehicle);
            assertNotNull(ticket);
        }

        assertEquals(22, parkingLot.getParkingLotService().getTotalAvailableSpots());
    }

    @Test
    public void testEntranceAndExitGates() {
        assertNotNull(parkingLot.getEntranceGate());
        assertNotNull(parkingLot.getExitGate());
        
        Vehicle car = new Vehicle("CAR010", SpotType.COMPACT, VehicleType.CAR);
        Ticket ticket = parkingLot.parkVehicle(car);
        
        Payment payment = parkingLot.exitVehicle(ticket.getTicketNumber());
        assertNotNull(payment);
    }

    @Test
    public void testParkingAttendant() {
        assertNotNull(parkingLot.getAttendant());
        
        Vehicle car = new Vehicle("CAR011", SpotType.REGULAR, VehicleType.CAR);
        parkingLot.parkVehicle(car);
        
        assertDoesNotThrow(() -> {
            parkingLot.generateParkingReport();
        });
    }
}
