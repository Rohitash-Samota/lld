package com.example.lld.booking.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.Movie;
import com.example.lld.booking.dto.Screen;
import com.example.lld.booking.dto.Show;
import com.example.lld.booking.dto.Theater;

@Service
public class ShowService {

    private final Map<String, Show> shows = new HashMap<>();

    public Show createShow(
            Movie movie,
            Theater theater,
            Screen screen,
            LocalDateTime startTime) {

        String showId = createShowId(movie, theater, screen, startTime);

        double durationHours = movie.getMovieDuration();

        LocalDateTime endTime = startTime.plusMinutes((long) (durationHours * 60));

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

    public Map<String, Show> getAllShows() {
        return shows;
    }

    public Show findShowById(String showId) {

        if (showId == null || showId.isBlank()) {
            return null;
        }

        return shows.get(showId);
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
