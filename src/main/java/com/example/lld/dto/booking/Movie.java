package com.example.lld.dto.booking;

public class Movie {

    private String movieId;
    private String movieName;
    private double movieDuration;
    private double basePrice;

    public Movie() {
    }

    public Movie(String movieName, double movieDuration) {
        this(movieName, movieDuration, 100.0);
    }

    public Movie(String movieName, double movieDuration, double basePrice) {
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.basePrice = basePrice;
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

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
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