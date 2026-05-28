package com.example.lld.dto.parking;

import com.example.lld.enums.parking.LevelType;
import com.example.lld.enums.parking.SpotStatus;
import com.example.lld.enums.parking.SpotType;

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

    public void initializeLevel(int numberRow, int spotPerRow) {

        int index = 0;

        for (int row = 0; row < numberRow; row++) {

            for (int col = 0; col < spotPerRow; col++) {

                ParkingSpot parkingSpot = new ParkingSpot();

                parkingSpot.setRow(row);
                parkingSpot.setColumn(col);
                parkingSpot.setVehicle(null);
                parkingSpot.setStatus(SpotStatus.AVAILABLE);

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
                parkingSpot.setSpotId(createSpotId(spotNumber));
                slots[index] = parkingSpot;
                index++;
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        SpotType spotNeeded = vehicle.getSpotNeeded();
        ParkingSpot availableParkingSpot = findAvailableSpot(spotNeeded);
        if (availableParkingSpot != null) {
            availableParkingSpot.ParkVehicle(vehicle);
            System.out.println("Vehicle => " + vehicle.getRCNumber() + " Spot Id =>" +
                    availableParkingSpot.getSpotId());
            return true;
        }

        System.out.println("Don't Available Parking Spot against " + spotNeeded);
        return false;
    }

    public boolean unparkVehicle(ParkingSpot spot) {
        if (!spot.isAvailableSpot()) {
            spot.unparkVehicle();
            System.out.println("Unpark vehicle =>" + spot.getSpotId());
            return true;
        }
        System.out.println("Already Unpark Vehicle =>" + spot.getSpotId());
        return false;
    }

    private ParkingSpot findAvailableSpot(SpotType spotType) {
        for (ParkingSpot spot : slots) {
            if (spot.isAvailableSpot()) {
                return spot;
            }
        }
        return null;
    }

    private String createSpotId(int spotNo) {
        return "L-" + levelId + "-SP-" + spotNo;
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