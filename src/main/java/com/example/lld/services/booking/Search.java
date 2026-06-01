package com.example.lld.services.booking;

import com.example.lld.dto.booking.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.lld.services.booking.TheatersService;
import com.example.lld.services.booking.MovieService;

@Service
public class Search {

    @Autowired
    private TheatersService theatersService;

    @Autowired
    private MovieService movieService;

    public Screen[] searchScreenByMovieName(String movieName) {
        return new Screen[0];
    }

    public Screen[] searchScreenByTheaterName(String theaterName) {
        return new Screen[0];
    }
}
