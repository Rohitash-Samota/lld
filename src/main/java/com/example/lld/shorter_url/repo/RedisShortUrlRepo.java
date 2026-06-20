package com.example.lld.shorter_url.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.lld.shorter_url.ShorterURLDto;

@Repository
public class RedisShortUrlRepo implements ShortURLRepositoryI {

    private final Map<String, ShorterURLDto> mappingByShortUrl = new HashMap<>();
    private final Map<String, ShorterURLDto> mappingByOriginalUrl = new HashMap<>();

    @Override
    public ShorterURLDto save(ShorterURLDto dto) {

        mappingByShortUrl.put(dto.getShortURL(), dto);
        mappingByOriginalUrl.put(dto.getOriginalURL(), dto);

        return dto;
    }

    @Override
    public Optional<ShorterURLDto> findByShortURL(String shortURL) {
        return Optional.ofNullable(mappingByShortUrl.get(shortURL));
    }

    @Override
    public Optional<ShorterURLDto> findByOriginalURL(String originalURL) {
        return Optional.ofNullable(mappingByOriginalUrl.get(originalURL));
    }

    @Override
    public void delete(String shortURL) {

        ShorterURLDto dto = mappingByShortUrl.remove(shortURL);

        if (dto != null) {
            mappingByOriginalUrl.remove(dto.getOriginalURL());
        }
    }
}