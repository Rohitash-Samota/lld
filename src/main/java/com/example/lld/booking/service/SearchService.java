package com.example.lld.booking.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.lld.booking.dto.Show;
import com.example.lld.booking.strategy.SearchStrategy;

@Service
public class SearchService {
    public Show[] search(String q, SearchStrategy searchStrategy) {
        Map<String, Show> shows = searchStrategy.search(q);

        return shows.values().toArray(new Show[0]);
    }
}
