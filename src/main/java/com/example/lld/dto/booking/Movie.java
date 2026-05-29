package com.example.lld.dto.booking;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private String movieId;
    private String movieName;
    private double movieDuration;

    private String genre;
    private String language;
    private String description;

    private LocalDate releaseDate;

    private double rating;

    private List<String> cast;
    private String director;

    public Movie() {
    }

    public Movie(String movieId,
                 String movieName,
                 double movieDuration,
                 String genre,
                 String language,
                 String description,
                 LocalDate releaseDate,
                 double rating,
                 List<String> cast,
                 String director) {

        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDuration = movieDuration;
        this.genre = genre;
        this.language = language;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.cast = cast;
        this.director = director;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieDuration=" + movieDuration +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", director='" + director + '\'' +
                '}';
    }
}