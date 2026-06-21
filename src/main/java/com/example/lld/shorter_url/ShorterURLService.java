package com.example.lld.shorter_url;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.lld.shorter_url.exception.InvalidShortURLException;
import com.example.lld.shorter_url.exception.ShortURLExpiredException;
import com.example.lld.shorter_url.exception.ShortURLNotFoundException;
import com.example.lld.shorter_url.repo.InMemoryShortUrlRepo;
import com.example.lld.shorter_url.repo.RedisShortUrlRepo;

@Service
public class ShorterURLService {

    private static final String SHORT_URL_HOST = "http://short.ly/";
    private static final Pattern URL_PATTERN = Pattern.compile("^https?://.+", Pattern.CASE_INSENSITIVE);

    private final RedisShortUrlRepo redisShortUrlRepo;
    private final InMemoryShortUrlRepo dbRepo;
    private final ShortURLGeneratorService shortURLGeneratorService;

    public ShorterURLService(RedisShortUrlRepo redisShortUrlRepo, InMemoryShortUrlRepo dbRepo,
            ShortURLGeneratorService shortURLGeneratorService) {
        this.redisShortUrlRepo = redisShortUrlRepo;
        this.dbRepo = dbRepo;
        this.shortURLGeneratorService = shortURLGeneratorService;

    }

    public ShorterURLDto createShortURL(String originalURL, LocalDateTime expiryDate) {
        String normalizedURL = normalizeUrl(originalURL);
        ShorterURLDto existing = lookupByOriginalURL(normalizedURL);

        if (existing != null && !isExpired(existing)) {
            return existing;
        }

        String id = shortURLGeneratorService.nextId();
        String shortURL = SHORT_URL_HOST + id;
        LocalDateTime effectiveExpiry = expiryDate != null ? expiryDate : LocalDateTime.now().plusDays(30);

        ShorterURLDto mapping = new ShorterURLDto(id, effectiveExpiry, normalizedURL, shortURL);
        dbRepo.save(mapping);
        redisShortUrlRepo.save(mapping);
        return mapping;
    }

    public String resolveShortURL(String shortURL) {
        if (shortURL == null || shortURL.isBlank()) {
            throw new InvalidShortURLException("Short URL cannot be null or empty");
        }

        String normalizedShortURL = normalizeShortURL(shortURL);
        ShorterURLDto mapping = lookupByShortURL(normalizedShortURL);

        if (mapping == null) {
            throw new ShortURLNotFoundException("Short URL not found: " + shortURL);
        }

        if (isExpired(mapping)) {
            throw new ShortURLExpiredException("Short URL has expired: " + shortURL);
        }

        return mapping.getOriginalURL();
    }

    public String resolveFullUrl(String originalURL) {

        String newUpdateUrl = normalizeUrl(originalURL);
        ShorterURLDto mapping = lookupByOriginalURL(newUpdateUrl);
        return mapping.getShortURL();
    }

    public ShorterURLDto getMapping(String shortURL) {
        String normalizedShortURL = normalizeShortURL(shortURL);
        return lookupByShortURL(normalizedShortURL);
    }

    private ShorterURLDto lookupByOriginalURL(String originalURL) {

        Optional<ShorterURLDto> mapping = redisShortUrlRepo.findByOriginalURL(originalURL);

        if (mapping.isPresent()) {
            return mapping.get();
        }

        mapping = dbRepo.findByOriginalURL(originalURL);

        if (mapping.isPresent()) {
            redisShortUrlRepo.save(mapping.get());
            return mapping.get();
        }

        return null;
    }

    private ShorterURLDto lookupByShortURL(String shortURL) {

        Optional<ShorterURLDto> mapping = redisShortUrlRepo.findByShortURL(shortURL);

        if (mapping.isPresent()) {
            return mapping.get();
        }

        mapping = dbRepo.findByShortURL(shortURL);

        if (mapping.isPresent()) {
            redisShortUrlRepo.save(mapping.get());
            return mapping.get();
        }

        return null;
    }

    private boolean isExpired(ShorterURLDto dto) {
        return dto.getExpiryDate() != null && dto.getExpiryDate().isBefore(LocalDateTime.now());
    }

    private String normalizeUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new InvalidShortURLException("Original URL cannot be null or empty");
        }

        String trimmed = url.trim();
        if (!URL_PATTERN.matcher(trimmed).matches()) {
            throw new InvalidShortURLException("Original URL must start with http:// or https://");
        }

        return trimmed;
    }

    private String normalizeShortURL(String shortURL) {
        String trimmed = shortURL.trim();
        if (trimmed.startsWith(SHORT_URL_HOST)) {
            return trimmed;
        }

        if (trimmed.matches("^[A-Za-z0-9_-]+$")) {
            return SHORT_URL_HOST + trimmed;
        }

        throw new InvalidShortURLException("Short URL must be either a full short URL or a valid short key");
    }

}
