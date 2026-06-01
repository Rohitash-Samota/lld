package com.example.lld.dto.booking;

import com.example.lld.enums.booking.ScreenType;

public class Screen {
    private String screenId;
    private ScreenType screenType;
    private String screenName;
    private Seat[] seats;

    public Screen(ScreenType screenType, String screenName, Seat[] seats) {
        this.screenType = screenType;
        this.screenName = screenName;
        this.seats = seats;
        this.screenId = getScreenId();
    }

    public ScreenType getScreenType() {
        return screenType;
    }

    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    private String createScreenId() {
        return "SC-" + getScreenName();
    }
}
