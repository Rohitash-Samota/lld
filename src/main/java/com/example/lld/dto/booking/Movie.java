package com.example.lld.dto.booking;

public class Movie {

    private String movieId;
    private String movieName;
    private double movieDuration;

    public Movie() {
    }

    public Movie(String movieName, double movieDuration) {

        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.movieId = createMovieId();
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public double getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(double movieDuration) {
        this.movieDuration = movieDuration;
    }

    private String createMovieId() {
        return "MV-" + getMovieName();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieDuration=" + movieDuration +
                '}';
    }
}