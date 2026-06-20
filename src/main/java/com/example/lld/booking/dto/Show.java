package com.example.lld.booking.dto;

import java.time.LocalDateTime;

public class Show {

    private String showId;
    private Movie movie;
    private Theater theater;
    private Screen screen;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Show(String showId, Movie movie, Theater theater, Screen screen,
            LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showId = showId;
    }

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
