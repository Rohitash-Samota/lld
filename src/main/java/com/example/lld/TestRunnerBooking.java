package com.example.lld;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.lld.dto.booking.Movie;
import com.example.lld.dto.booking.Screen;
import com.example.lld.dto.booking.Show;
import com.example.lld.dto.booking.Theater;
import com.example.lld.services.booking.MovieService;
import com.example.lld.services.booking.SearchService;
import com.example.lld.services.booking.ShowService;
import com.example.lld.services.booking.TheatersService;
import com.example.lld.strategy.booking.SearchByCityName;
import com.example.lld.strategy.booking.SearchByMovieTitleName;
import com.example.lld.strategy.booking.SearchByTheaterName;

@Component
public class TestRunnerBooking implements CommandLineRunner {

    private final ShowService showService;
    private final MovieService movieService;
    private final TheatersService theatersService;
    private final SearchService searchService;
    private final SearchByCityName searchByCityName;
    private final SearchByMovieTitleName searchByMovieTitleName;
    private final SearchByTheaterName searchByTheaterName;

    public TestRunnerBooking(
            ShowService showService,
            MovieService movieService,
            TheatersService theatersService,
            SearchService searchService,
            SearchByCityName searchByCityName,
            SearchByMovieTitleName searchByMovieTitleName,
            SearchByTheaterName searchByTheaterName) {

        this.showService = showService;
        this.movieService = movieService;
        this.theatersService = theatersService;
        this.searchService = searchService;
        this.searchByCityName = searchByCityName;
        this.searchByMovieTitleName = searchByMovieTitleName;
        this.searchByTheaterName = searchByTheaterName;

    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("========== MOVIE BOOKING SYSTEM ==========");

        // Create Movies
        Movie movieA = movieService.createMovie("Avengers Endgame", 3.0);
        Movie movieB = movieService.createMovie("Interstellar", 2.8);
        Movie movieC = movieService.createMovie("Jawan", 2.5);

        // Create Theaters
        Theater theaterA = theatersService.initializeTheater(
                "PVR Saket",
                "Delhi",
                3,
                10);

        Theater theaterB = theatersService.initializeTheater(
                "INOX World Trade Park",
                "Jaipur",
                4,
                12);

        Theater theaterC = theatersService.initializeTheater(
                "Cinepolis Sikar",
                "Sikar",
                5,
                10);

        Screen screen1 = theaterA.getScreens()[0];
        Screen screen2 = theaterB.getScreens()[0];
        Screen screen3 = theaterC.getScreens()[0];

        Show show1 = showService.createShow(
                movieA,
                theaterA,
                screen1,
                LocalDateTime.now());

        Show show2 = showService.createShow(
                movieB,
                theaterB,
                screen2,
                LocalDateTime.now());

        Show show3 = showService.createShow(
                movieC,
                theaterB,
                screen3,
                LocalDateTime.now());

        Show[] showsByCity = searchService.search("delhi", searchByCityName);

        System.out.println("\n========== SEARCH RESULTS FOR 'delhi' ==========");
        System.out.println("Found " + showsByCity.length + " show(s) in Delhi:");
        for (Show foundShow : showsByCity) {
            System.out.println("ShowId: " + foundShow.getShowId()
                    + ", Movie: " + foundShow.getMovie().getMovieName()
                    + ", Theater: " + foundShow.getTheater().getName()
                    + ", City: " + foundShow.getTheater().getAddress().getCity()
                    + ", Screen: " + (foundShow.getScreen() != null ? foundShow.getScreen().getScreenName() : "N/A"));
        }

        Show[] showByMovieName = searchService.search("Avengers", searchByMovieTitleName);

        System.out.println("\n========== SEARCH RESULTS FOR 'delhi' ==========");
        System.out.println("Found " + showByMovieName.length + " show(s) in Delhi:");
        for (Show foundShow : showByMovieName) {
            System.out.println("ShowId: " + foundShow.getShowId()
                    + ", Movie: " + foundShow.getMovie().getMovieName()
                    + ", Theater: " + foundShow.getTheater().getName()
                    + ", City: " + foundShow.getTheater().getAddress().getCity()
                    + ", Screen: " + (foundShow.getScreen() != null ? foundShow.getScreen().getScreenName() : "N/A"));
        }

        Show[] showByTheaterName = searchService.search("Avengers", searchByTheaterName);

        System.out.println("\n========== SEARCH RESULTS FOR 'delhi' ==========");
        System.out.println("Found " + showByTheaterName.length + " show(s) in Delhi:");
        for (Show foundShow : showByTheaterName) {
            System.out.println("ShowId: " + foundShow.getShowId()
                    + ", Movie: " + foundShow.getMovie().getMovieName()
                    + ", Theater: " + foundShow.getTheater().getName()
                    + ", City: " + foundShow.getTheater().getAddress().getCity()
                    + ", Screen: " + (foundShow.getScreen() != null ? foundShow.getScreen().getScreenName() : "N/A"));
        }
        System.out.println("\n========== DATA LOADED ==========");
    }
}