package com.example.lld.services;

import com.example.lld.dto.parking.Level;
import com.example.lld.dto.parking.ParkingSpot;
import com.example.lld.dto.parking.Vehicle;
import com.example.lld.enums.parking.LevelType;

public class ParkingService {
    private Level[] levels;

    public ParkingService(int row, int col) {
        levels = new Level[3];
        levels[0] = new Level(LevelType.ZERO, row, col);
        levels[1] = new Level(LevelType.ONE, row, col);
        levels[2] = new Level(LevelType.TWO, row, col);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Level level : levels) {
            boolean isPark = level.parkVehicle(vehicle);
            if (isPark) {
                System.out.println("Park Vehicle => " + vehicle.getRCNumber());
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

        System.out.println("Vehicle unparked from " + spot.getSpotId());

        return true;
    }
}
