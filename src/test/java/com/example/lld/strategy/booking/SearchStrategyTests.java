package com.example.lld.strategy.booking;

import com.example.lld.dto.booking.Address;
import com.example.lld.dto.booking.Movie;
import com.example.lld.dto.booking.Screen;
import com.example.lld.dto.booking.Seat;
import com.example.lld.dto.booking.Show;
import com.example.lld.dto.booking.Theater;
import com.example.lld.enums.booking.ScreenType;
import com.example.lld.services.booking.SearchService;
import com.example.lld.services.booking.ShowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchStrategyTests {

    private ShowService showService;
    private Show avengersShow;
    private Show inceptionShow;

    @BeforeEach
    void setUp() {
        showService = new ShowService();

        Movie avengers = new Movie("Avengers", 2.5);
        Movie inception = new Movie("Inception", 2.8);

        Screen screenOne = new Screen(ScreenType.TWO_D, "Screen 1", new Seat[0]);
        Screen screenTwo = new Screen(ScreenType.THREE_D, "Screen 2", new Seat[0]);

        Theater cineplex = new Theater("Cineplex", new Address("New York"), new Screen[] { screenOne });
        Theater grandCinema = new Theater("Grand Cinema", new Address("Los Angeles"), new Screen[] { screenTwo });

        avengersShow = showService.createShow(avengers, cineplex, screenOne,
                LocalDateTime.of(2026, 1, 1, 10, 0));
        inceptionShow = showService.createShow(inception, grandCinema, screenTwo,
                LocalDateTime.of(2026, 1, 2, 20, 0));
    }

    @Test
    void searchByMovieTitleName_returnsMatchingShows() throws Exception {
        SearchByMovieTitleName strategy = new SearchByMovieTitleName();
        injectShowService(strategy);

        assertEquals(1, strategy.search("avengers").size());
        assertTrue(strategy.search("avengers").values().stream()
                .anyMatch(show -> "Avengers".equals(show.getMovie().getMovieName())));
    }

    @Test
    void searchByTheaterName_returnsMatchingShows() throws Exception {
        SearchByTheaterName strategy = new SearchByTheaterName();
        injectShowService(strategy);

        assertEquals(1, strategy.search("grand").size());
        assertTrue(strategy.search("grand").values().stream()
                .anyMatch(show -> "Grand Cinema".equals(show.getTheater().getName())));
    }

    @Test
    void searchByCityName_returnsMatchingShows() throws Exception {
        SearchByCityName strategy = new SearchByCityName();
        injectShowService(strategy);

        assertEquals(1, strategy.search("los").size());
        assertTrue(strategy.search("los").values().stream()
                .anyMatch(show -> "Los Angeles".equals(show.getTheater().getAddress().getCity())));
    }

    @Test
    void searchService_convertsSearchResultsToArray() throws Exception {
        SearchByMovieTitleName strategy = new SearchByMovieTitleName();
        injectShowService(strategy);

        Show[] results = new SearchService().search("Avengers", strategy);

        assertEquals(1, results.length);
        assertEquals("Avengers", results[0].getMovie().getMovieName());
    }

    @Test
    void searchService_searchByCityName_returnsMatchingShows() throws Exception {
        SearchByCityName strategy = new SearchByCityName();
        injectShowService(strategy);

        Show[] results = new SearchService().search("los", strategy);

        assertEquals(1, results.length);
        assertEquals("Los Angeles", results[0].getTheater().getAddress().getCity());
    }

    private void injectShowService(SearchStrategy strategy) throws Exception {
        Field field = strategy.getClass().getDeclaredField("showService");
        field.setAccessible(true);
        field.set(strategy, showService);
    }
}
