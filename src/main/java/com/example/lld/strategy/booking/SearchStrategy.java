package com.example.lld.strategy.booking;

import java.util.Map;

import com.example.lld.dto.booking.Show;

public interface SearchStrategy {
    Map<String, Show> search(String q);
}
