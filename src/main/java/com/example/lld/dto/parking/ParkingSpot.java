package com.example.lld.dto.parking;

import com.example.lld.enums.parking.SpotStatus;
import com.example.lld.enums.parking.SpotType;

public class ParkingSpot {

    private String spotId;
    private Vehicle vehicle;
    private int row;
    private int column;
    private int spotNumber;
    private SpotType spotType;
    private SpotStatus status;

    public void ParkingSpot(String spotId, int row, int column, int spotNumber, SpotType spotType) {
        this.spotId = spotId;
        this.row = row;
        this.column = column;
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.status = SpotStatus.AVAILABLE;
    }

    public boolean isAvailableSpot() {
        return this.getStatus() == SpotStatus.AVAILABLE;
    }

    public boolean ParkVehicle(Vehicle vehicle) {
        if (!isAvailableSpot()) {
            return false;
        }
        this.vehicle = vehicle;
        this.status = SpotStatus.OCCUPIED;
        return true;
    }

    public boolean unparkVehicle() {
        if (!isAvailableSpot()) {
            return false;
        }
        this.status = SpotStatus.AVAILABLE;
        this.vehicle = null;
        return true;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public SpotStatus getStatus() {
        return status;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }

}
