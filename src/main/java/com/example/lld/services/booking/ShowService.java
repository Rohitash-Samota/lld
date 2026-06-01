package com.example.lld.services.booking;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.dto.booking.Movie;
import com.example.lld.dto.booking.Screen;
import com.example.lld.dto.booking.Show;
import com.example.lld.dto.booking.Theater;

@Service
public class ShowService {

    private final Map<String, Show> shows = new HashMap<>();

    public Show createShow(
            Movie movie,
            Theater theater,
            Screen screen,
            LocalDateTime startTime,
            LocalDateTime endTime) {

        String showId = createShowId(movie, theater, screen, startTime);

        Show show = new Show(
                showId,
                movie,
                theater,
                screen,
                startTime,
                endTime);

        shows.put(showId, show);

        return show;
    }

    private String createShowId(
            Movie movie,
            Theater theater,
            Screen screen,
            LocalDateTime startTime) {

        return "SHOW-" +
                movie.getMovieId() + "-" +
                theater.getTheaterId() + "-" +
                screen.getScreenId() + "-" +
                startTime.toLocalDate() + "-" +
                startTime.getHour();
    }
}
