package com.example.lld.booking.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.lld.booking.dto.Show;
import com.example.lld.booking.service.ShowService;

@Component
public class SearchByMovieTitleName implements SearchStrategy {
    @Autowired
    private ShowService showService;

    @Override
    public Map<String, Show> search(String q) {
        Map<String, Show> shows = showService.getAllShows();
        Map<String, Show> filteredShowsMap = new HashMap<>();
        shows.forEach((showId, show) -> {
            String movieName = show.getMovie().getMovieName();
            if (Pattern.compile(".*" + Pattern.quote(q) + ".*", Pattern.CASE_INSENSITIVE).matcher(movieName)
                    .matches()) {
                filteredShowsMap.put(showId, show);
            }
        });
        return filteredShowsMap;
    }
}
