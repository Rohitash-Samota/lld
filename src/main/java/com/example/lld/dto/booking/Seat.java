package com.example.lld.dto.booking;

import com.example.lld.enums.booking.SeatType;

public class Seat {

    private String seatId;
    private String seatName;
    private char row;
    private int colNumber;
    private boolean isBooked;
    private SeatType seatType;

    public Seat(String seatId, char row, int colNumber, SeatType seatType) {
        this.seatId = seatId;
        this.row = row;
        this.colNumber = colNumber;
        this.seatType = seatType;
        this.isBooked = false;
        this.seatName = row + String.valueOf(colNumber);
    }

    public String getSeatId() {
        return seatId;
    }

    public String getSeatName() {
        return seatName;
    }

    public char getRow() {
        return row;
    }

    public int getColNumber() {
        return colNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}