package com.example.lld.shorter_url.repo;

import java.util.Optional;

import com.example.lld.shorter_url.ShorterURLDto;

public interface ShortURLRepositoryI {

    ShorterURLDto save(ShorterURLDto dto);

    Optional<ShorterURLDto> findByShortURL(String shortURL);

    Optional<ShorterURLDto> findByOriginalURL(String originalURL);

    void delete(String shortURL);
}