package com.example.lld.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.Screen;
import com.example.lld.booking.dto.Seat;
import com.example.lld.booking.enums.ScreenType;

@Service
public class ScreensService {

    @Autowired
    private SeatsService seatsService;

    private final List<Screen> screens = new ArrayList<>();

    public Screen createScreen(ScreenType screenType, String screenName, int seatCols) {

        Seat[] seats = seatsService.initializeSeats(seatCols);

        Screen screen = new Screen(screenType, screenName, seats);

        screens.add(screen);

        return screen;
    }

    public List<Screen> getAllScreens() {
        return screens;
    }
}