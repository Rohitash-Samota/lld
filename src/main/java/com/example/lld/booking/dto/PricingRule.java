package com.example.lld.booking.dto;

import com.example.lld.booking.enums.ScreenType;
import com.example.lld.booking.enums.SeatType;

public class PricingRule {
    private String movieId;
    private String theaterId;
    private ScreenType screenType;
    private SeatType seatType;
    private double amount;
    private double weekendSurcharge;
    private double festivalSurcharge;

    public PricingRule(String movieId, String theaterId, ScreenType screenType,
            SeatType seatType, double amount, double weekendSurcharge,
            double festivalSurcharge) {
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.screenType = screenType;
        this.seatType = seatType;
        this.amount = amount;
        this.weekendSurcharge = weekendSurcharge;
        this.festivalSurcharge = festivalSurcharge;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public double getAmount() {
        return amount;
    }

    public double getWeekendSurcharge() {
        return weekendSurcharge;
    }

    public double getFestivalSurcharge() {
        return festivalSurcharge;
    }

    public boolean matches(Show show, Seat seat) {
        if (show == null || seat == null) {
            return false;
        }

        return show.getMovie() != null
                && show.getTheater() != null
                && show.getScreen() != null
                && show.getMovie().getMovieId() != null
                && show.getTheater().getTheaterId() != null
                && show.getScreen().getScreenType() == screenType
                && seat.getSeatType() == seatType
                && show.getMovie().getMovieId().equals(movieId)
                && show.getTheater().getTheaterId().equals(theaterId);
    }
}
