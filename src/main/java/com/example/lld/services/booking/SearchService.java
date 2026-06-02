package com.example.lld.services.booking;

import com.example.lld.dto.booking.Show;
import com.example.lld.strategy.booking.SearchStrategy;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SearchService {
    public Show[] search(String q, SearchStrategy searchStrategy) {
        Map<String, Show> shows = searchStrategy.search(q);

        return shows.values().toArray(new Show[0]);
    }
}
