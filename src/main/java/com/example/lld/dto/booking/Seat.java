package com.example.lld.dto.booking;

public class Seat {

    private String seatName;
    private int colNumber;
    private int rowNumber;
    private boolean isBooked;

    public Seat(int colNumber, int rowNumber, SeatType seatType) {
        this.colNumber = colNumber;
        this.rowNumber = rowNumber;
        this.seatName = generateSeatName(colNumber, rowNumber);
        this.isBooked = false;
        this.seatType = seatType;
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
