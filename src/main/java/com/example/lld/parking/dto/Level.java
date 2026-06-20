package com.example.lld.parking.dto;

import com.example.lld.parking.enums.LevelType;
import com.example.lld.parking.enums.SpotType;

public class Level {

    private String levelId;
    private LevelType level;
    private ParkingSpot[] slots;
    private int totalSpots;
    private int availableSpots;

    public Level(LevelType level, int numberRow, int spotPerRow) {

        this.levelId = createLevelId(level);
        this.level = level;

        this.totalSpots = numberRow * spotPerRow;
        this.availableSpots = this.totalSpots;

        this.slots = new ParkingSpot[this.totalSpots];

        initializeLevel(numberRow, spotPerRow);
    }

    private void initializeLevel(int numberRow, int spotPerRow) {

        int index = 0;

        for (int row = 0; row < numberRow; row++) {

            for (int col = 0; col < spotPerRow; col++) {

                ParkingSpot parkingSpot = new ParkingSpot(row, col);

                int spotNumber = index + 1;

                parkingSpot.setSpotNumber(spotNumber);

                if (spotNumber <= totalSpots * 0.20) {
                    parkingSpot.setSpotType(SpotType.COMPACT);

                } else if (spotNumber <= totalSpots * 0.40) {
                    parkingSpot.setSpotType(SpotType.REGULAR);

                } else if (spotNumber <= totalSpots * 0.60) {
                    parkingSpot.setSpotType(SpotType.LARGE);

                } else if (spotNumber <= totalSpots * 0.80) {
                    parkingSpot.setSpotType(SpotType.EXTRA_LARGE);

                } else {
                    parkingSpot.setSpotType(SpotType.LUXURY);
                }
                slots[index] = parkingSpot;
                index++;
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle, String ticketId) {
        SpotType spotNeeded = vehicle.getSpotNeeded();
        ParkingSpot availableParkingSpot = findAvailableSpot(spotNeeded);
        if (availableParkingSpot != null) {
            availableParkingSpot.ParkVehicle(vehicle.getRCNumber(), ticketId);
            System.out.println("Vehicle => " + vehicle.getRCNumber() + " Spot Id =>"
                    + availableParkingSpot.getSpotId());
            return true;
        }

        System.out.println("Don't Available Parking Spot against " + spotNeeded);
        return false;
    }

    public boolean unparkVehicle(ParkingSpot spot) {

        boolean success = spot.unparkVehicle();

        if (success) {
            System.out.println("Vehicle un-parked from " + spot.getSpotId());
        } else {
            System.out.println("Spot already empty: " + spot.getSpotId());
        }

        return success;
    }

    public ParkingSpot getParkingSpotFromLevel(String ticketId) {

        if (slots == null) {
            return null;
        }

        for (ParkingSpot spot : slots) {
            if (spot != null
                    && spot.getTicketNumber() != null
                    && spot.getTicketNumber().equals(ticketId)) {
                return spot;
            }
        }

        return null;
    }

    private ParkingSpot findAvailableSpot(SpotType spotType) {
        for (ParkingSpot spot : slots) {
            if (spot.isAvailableSpotGivenSpotType(spotType)) {
                return spot;
            }
        }
        return null;
    }

    public ParkingSpot[] getAvailableSpots() {
        return slots;
    }

    public int getAvailableSpotsCount() {
        return availableSpots;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public LevelType getLevel() {
        return level;
    }

    public void setLevel(LevelType level) {
        this.level = level;
    }

    public ParkingSpot[] getSlots() {
        return slots;
    }

    public void setSlots(ParkingSpot[] slots) {
        this.slots = slots;
    }

    public int getTotalSpots() {
        return totalSpots;
    }

    public void setTotalSpots(int totalSpots) {
        this.totalSpots = totalSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    public String createLevelId(LevelType levelType) {
        return "L-" + levelType;
    }
}
