package com.example.lld.parking.dto;

import com.example.lld.parking.enums.SpotStatus;
import com.example.lld.parking.enums.SpotType;

public class ParkingSpot {

    private String spotId;
    private String rcNumber;
    private String ticketNumber;
    private int row;
    private int column;
    private int spotNumber;
    private SpotType spotType;
    private SpotStatus status;

    public ParkingSpot() {

    }

    public ParkingSpot(int row, int column) {
        this.spotId = createSpotId(spotNumber);
        this.row = row;
        this.column = column;
        this.spotNumber = row * column;
        this.status = SpotStatus.AVAILABLE;
    }

    public boolean isAvailableSpot() {
        return this.getStatus() == SpotStatus.AVAILABLE;
    }

    public boolean isAvailableSpotGivenSpotType(SpotType spotType) {
        return this.getStatus() == SpotStatus.AVAILABLE && this.getSpotType() == spotType;
    }

    public boolean ParkVehicle(String rcNumber, String ticketNumber) {
        if (!isAvailableSpot()) {
            return false;
        }
        this.rcNumber = rcNumber;
        this.ticketNumber = ticketNumber;
        this.status = SpotStatus.OCCUPIED;
        return true;
    }

    public boolean unparkVehicle() {
        if (isAvailableSpot()) {
            return false;
        }
        this.status = SpotStatus.AVAILABLE;
        this.ticketNumber = null;
        this.rcNumber = null;
        return true;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
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

    public String getRcNumber() {
        return rcNumber;
    }

    public void setRcNumber(String rcNumber) {
        this.rcNumber = rcNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    private String createSpotId(int spotNo) {
        return "SP-" + spotNo;
    }
}
