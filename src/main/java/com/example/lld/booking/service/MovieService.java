package com.example.lld.booking.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.Movie;

@Service
public class MovieService {
    private final Map<String, Movie> movies = new HashMap<>();

    public Movie createMovie(String movieName, double movieDuration) {
        return createMovie(movieName, movieDuration, 100.0);
    }

    public Movie createMovie(String movieName, double movieDuration,
            double basePrice) {
        Movie newMovie = new Movie(movieName, movieDuration, basePrice);
        movies.put(newMovie.getMovieId(), newMovie);
        return newMovie;
    }

    public Map<String, Movie> getAllMovies() {
        return this.movies;
    }
}
