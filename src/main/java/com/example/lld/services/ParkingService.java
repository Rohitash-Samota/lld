package com.example.lld.services;

import org.springframework.stereotype.Service;

import com.example.lld.dto.parking.Level;
import com.example.lld.dto.parking.ParkingSpot;
import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.LevelType;

@Service
public class ParkingService {

    private final Level[] levels;

    public ParkingService() {
        int row = 3;
        int col = 3;
        this.levels = new Level[3];
        this.levels[0] = new Level(LevelType.ZERO, row, col);
        this.levels[1] = new Level(LevelType.ONE, row, col);
        this.levels[2] = new Level(LevelType.TWO, row, col);
    }

    public boolean parkVehicle(Vehicle vehicle, String ticketId) {
        for (Level level : levels) {
            boolean isPark = level.parkVehicle(vehicle, ticketId);
            if (isPark) {
                System.out.println("Park Vehicle => " + vehicle.getRCNumber() + " Against Ticket Id =>" + ticketId);
                return true;
            }
        }
        return false;
    }

    public boolean unparkVehicle(ParkingSpot spot) {

        if (spot.isAvailableSpot()) {
            return false;
        }

        spot.unparkVehicle();

        System.out.println("Vehicle un-parked from " + spot.getSpotId());

        return true;
    }

    public Level[] getAllLevels() {
        return this.levels;
    }

    public ParkingSpot getParkingSpot(String ticketId) {
        for (Level level : levels) {
            ParkingSpot parkingSpot = level.getParkingSpotFromLevel(ticketId);

            if (parkingSpot != null) {
                return parkingSpot;
            }
        }

        return null;
    }
}
