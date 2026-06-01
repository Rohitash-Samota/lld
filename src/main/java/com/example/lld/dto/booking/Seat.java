package com.example.lld.dto.booking;

import com.example.lld.enums.booking.SeatType;

public class Seat {

    private String seatId;
    private String seatName;
    private int colNumber;
    private int rowNumber;
    private boolean isBooked;
    private SeatType seatType;

    public Seat(int colNumber, int rowNumber, SeatType seatType) {
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
        this.seatName = generateSeatName(colNumber, rowNumber);
        this.isBooked = false;
        this.seatType = seatType;
        this.seatId = createSeatId();
    }

    private String generateSeatName(int colNumber, int rowNumber) {
        char rowChar = (char) ('A' + rowNumber - 1);
        return rowChar + String.valueOf(colNumber);
    }

    public String getSeatName() {
        return seatName;
    }

    public int getColNumber() {
        return colNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        this.isBooked = true;
    }

    public void cancelSeat() {
        this.isBooked = false;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    private String createSeatId() {
        return "S-" + getColNumber() + '-' + getRowNumber();
    }

    @Override
    public String toString() {
        return "Seat{"
                + "seatName='" + seatName + '\''
                + ", colNumber=" + colNumber
                + ", rowNumber=" + rowNumber
                + ", isBooked=" + isBooked
                + ", Seat Type= " + seatType
                + '}';
    }
}
