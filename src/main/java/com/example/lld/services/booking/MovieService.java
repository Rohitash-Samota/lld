package com.example.lld.services.booking;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.dto.booking.Movie;

@Service
public class MovieService {
    private final Map<String, Movie> movies = new HashMap<>();

    public Movie createMovie(String movieName, double movieDuration) {
        Movie newMovie = new Movie(movieName, movieDuration);
        movies.put(newMovie.getMovieId(), newMovie);
        return newMovie;
    }

    public Map<String, Movie> getAllMovies() {
        return this.movies;
    }
}
