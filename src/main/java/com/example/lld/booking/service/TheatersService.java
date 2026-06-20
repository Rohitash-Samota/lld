package com.example.lld.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.Address;
import com.example.lld.booking.dto.Screen;
import com.example.lld.booking.dto.Theater;
import com.example.lld.booking.enums.ScreenType;

@Service
public class TheatersService {

    private static final int SCREEN_TYPE_COUNT = ScreenType.values().length;

    @Autowired
    private ScreensService screensService;

    private final List<Theater> theaters = new ArrayList<>();

    public Theater initializeTheater(String theaterName, String city, int screenNo, int seatCols) {

        Screen[] screens = new Screen[screenNo];
        ScreenType[] screenTypes = ScreenType.values();
        for (int i = 0; i < screenNo; i++) {
            screens[i] = screensService.createScreen(screenTypes[i % SCREEN_TYPE_COUNT], "Screen-" + (i + 1), seatCols);
        }
        Address address = new Address();
        address.setCity(city);
        Theater theater = new Theater(theaterName, address, screens);
        theaters.add(theater);
        return theater;
    }

    public List<Theater> getAllTheaters() {
        return theaters;
    }

    public List<Theater> searchTheatersByName(String theaterName) {
        if (theaterName.length() < 3) {
            System.out.println("Search THeaters By Name : Provide minimum 4 char for search");
        }
        List<Theater> matchedTheaters = new ArrayList<>();

        for (Theater theater : theaters) {
            if (theater.getName() != null &&
                    theater.getName().toLowerCase().contains(theaterName.toLowerCase())) {

                matchedTheaters.add(theater);
            }
        }

        return matchedTheaters;
    }

    public List<Theater> searchTheatersByCity(String cityName) {
        if (cityName.length() < 3) {
            System.out.println("Search THeaters By cityName : Provide minimum 3 char for search");
        }
        List<Theater> matchedTheaters = new ArrayList<>();
        for (Theater theater : theaters) {
            String theaterCityName = theater.getAddress().getCity();
            if (theaterCityName != null && theaterCityName.contains(cityName)) {
                matchedTheaters.add(theater);
            }
        }
        return matchedTheaters;
    }
}