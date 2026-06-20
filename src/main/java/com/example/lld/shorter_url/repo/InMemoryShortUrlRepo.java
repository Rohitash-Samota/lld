package com.example.lld.shorter_url.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.lld.shorter_url.ShorterURLDto;

public class InMemoryShortUrlRepo implements ShortURLRepositoryI {

    private final Map<String, ShorterURLDto> byShortUrl = new HashMap<>();
    private final Map<String, ShorterURLDto> byOriginalUrl = new HashMap<>();

    @Override
    public ShorterURLDto save(ShorterURLDto dto) {

        byShortUrl.put(dto.getShortURL(), dto);
        byOriginalUrl.put(dto.getOriginalURL(), dto);

        return dto;
    }

    @Override
    public Optional<ShorterURLDto> findByShortURL(String shortURL) {
        return Optional.ofNullable(byShortUrl.get(shortURL));
    }

    @Override
    public Optional<ShorterURLDto> findByOriginalURL(String originalURL) {
        return Optional.ofNullable(byOriginalUrl.get(originalURL));
    }

    @Override
    public void delete(String shortURL) {

        ShorterURLDto dto = byShortUrl.remove(shortURL);

        if (dto != null) {
            byOriginalUrl.remove(dto.getOriginalURL());
        }
    }
}