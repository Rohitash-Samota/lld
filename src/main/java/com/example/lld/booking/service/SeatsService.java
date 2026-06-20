package com.example.lld.booking.service;

import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.Seat;
import com.example.lld.booking.enums.SeatType;

@Service
public class SeatsService {

    private static final char[] ROWS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public Seat[] initializeSeats(int cols) {

        Seat[] seats = new Seat[ROWS.length * cols];
        int index = 0;

        for (char row : ROWS) {

            for (int col = 1; col <= cols; col++) {

                SeatType seatType;

                if (row <= 'I') {
                    seatType = SeatType.SILVER;
                } else if (row <= 'M') {
                    seatType = SeatType.GOLD;
                } else {
                    seatType = SeatType.PLATINUM;
                }

                seats[index++] = new Seat(row, col, seatType);
            }
        }

        return seats;
    }
}