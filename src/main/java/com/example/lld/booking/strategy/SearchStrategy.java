package com.example.lld.booking.strategy;

import java.util.Map;

import com.example.lld.booking.dto.Show;

public interface SearchStrategy {
    Map<String, Show> search(String q);
}
