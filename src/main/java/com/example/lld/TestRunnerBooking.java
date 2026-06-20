package com.example.lld;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.lld.booking.dto.Movie;
import com.example.lld.booking.dto.Screen;
import com.example.lld.booking.dto.Seat;
import com.example.lld.booking.dto.Show;
import com.example.lld.booking.dto.Theater;
import com.example.lld.booking.enums.SeatType;
import com.example.lld.booking.service.BookingService;
import com.example.lld.booking.service.MovieService;
import com.example.lld.booking.service.PricingRuleService;
import com.example.lld.booking.service.PricingService;
import com.example.lld.booking.service.SearchService;
import com.example.lld.booking.service.ShowService;
import com.example.lld.booking.service.TheatersService;
import com.example.lld.booking.strategy.SearchByCityName;
import com.example.lld.booking.strategy.SearchByMovieTitleName;
import com.example.lld.booking.strategy.SearchByTheaterName;

@Component
public class TestRunnerBooking implements CommandLineRunner {

        private final ShowService showService;
        private final MovieService movieService;
        private final TheatersService theatersService;
        private final SearchService searchService;
        private final SearchByCityName searchByCityName;
        private final SearchByMovieTitleName searchByMovieTitleName;
        private final SearchByTheaterName searchByTheaterName;
        private final PricingService pricingService;
        private final PricingRuleService pricingRuleService;
        private final BookingService bookingService;

        public TestRunnerBooking(
                        ShowService showService,
                        MovieService movieService,
                        TheatersService theatersService,
                        SearchService searchService,
                        SearchByCityName searchByCityName,
                        SearchByMovieTitleName searchByMovieTitleName,
                        SearchByTheaterName searchByTheaterName,
                        PricingService pricingService,
                        PricingRuleService pricingRuleService,
                        BookingService bookingService) {

                this.showService = showService;
                this.movieService = movieService;
                this.theatersService = theatersService;
                this.searchService = searchService;
                this.searchByCityName = searchByCityName;
                this.searchByMovieTitleName = searchByMovieTitleName;
                this.searchByTheaterName = searchByTheaterName;
                this.pricingService = pricingService;
                this.pricingRuleService = pricingRuleService;
                this.bookingService = bookingService;

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

                pricingRuleService.addPricingRule(movieA.getMovieId(), theaterA.getTheaterId(),
                                screen1.getScreenType(), SeatType.GOLD, 120.0, 25.0, 50.0);

                Seat pricingSeat = null;
                for (Seat seat : screen1.getSeats()) {
                        if (seat != null && SeatType.GOLD.equals(seat.getSeatType())) {
                                pricingSeat = seat;
                                break;
                        }
                }

                if (pricingSeat != null) {
                        double ticketAmount = pricingService.calculateTicketPrice(show1, pricingSeat, true, true);
                        System.out.println("\n========== PRICING DEMO ==========");
                        System.out.println("Movie: " + show1.getMovie().getMovieName());
                        System.out.println("Theater: " + show1.getTheater().getName());
                        System.out.println("Seat: " + pricingSeat.getSeatName());
                        System.out.println("Seat type: " + pricingSeat.getSeatType());
                        System.out.println("Weekend + festival ticket amount: " + ticketAmount);

                        com.example.lld.booking.dto.Ticket ticket = bookingService.bookTicket(
                                        show1.getShowId(), pricingSeat.getRow(),
                                        pricingSeat.getColNumber(), true, true);
                        if (ticket != null) {
                                System.out.println("Booked ticket " + ticket.getTicketNumber()
                                                + " for amount " + ticket.getAmount());
                        }
                }

                Show[] showsByCity = searchService.search("delhi", searchByCityName);

                System.out.println("\n========== SEARCH RESULTS FOR 'delhi' ==========");
                System.out.println("Found " + showsByCity.length + " show(s) in Delhi:");
                for (Show foundShow : showsByCity) {
                        System.out.println("ShowId: " + foundShow.getShowId()
                                        + ", Movie: " + foundShow.getMovie().getMovieName()
                                        + ", Theater: " + foundShow.getTheater().getName()
                                        + ", City: " + foundShow.getTheater().getAddress().getCity()
                                        + ", Screen: "
                                        + (foundShow.getScreen() != null ? foundShow.getScreen().getScreenName()
                                                        : "N/A"));
                }

                System.out.println("\n========== DATA LOADED ==========");
        }
}