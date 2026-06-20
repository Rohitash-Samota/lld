package com.example.lld.shorter_url;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.lld.shorter_url.exception.InvalidShortURLException;
import com.example.lld.shorter_url.exception.ShortURLExpiredException;
import com.example.lld.shorter_url.exception.ShortURLNotFoundException;
import com.example.lld.shorter_url.repo.RedisShortUrlRepo;
import com.example.lld.shorter_url.repo.InMemoryShortUrlRepository;

class ShorterURLServiceTests {

    private InMemoryShortUrlRepository repo;
    private RedisShortUrlRepo redisRepo;
    private ShorterURLService service;

    @BeforeEach
    void setUp() {
        repo = new InMemoryShortUrlRepository();
        redisRepo = new RedisShortUrlRepo();
        service = new ShorterURLService(repo, redisRepo);
    }

    @Test
    void createShortURL_generatesShortUrlAndResolvesBack() {
        String original = "https://example.com/page/1";
        ShorterURLDto dto = service.createShortURL(original);

        assertTrue(dto.getShortURL().contains("http://short.ly/"));
        assertEquals(original, service.resolveShortURL(dto.getShortURL()));
    }

    @Test
    void createShortURL_returnsSameMappingForDuplicateOriginalURL() {
        String original = "https://example.com/page/2";
        ShorterURLDto first = service.createShortURL(original);
        ShorterURLDto second = service.createShortURL(original);

        assertEquals(first.getId(), second.getId());
        assertEquals(first.getShortURL(), second.getShortURL());
    }

    @Test
    void resolveShortURL_throwsWhenShortUrlNotFound() {
        assertThrows(ShortURLNotFoundException.class, () -> service.resolveShortURL("http://short.ly/unknown"));
    }

    @Test
    void resolveShortURL_readsFromRedisFirst() {
        ShorterURLDto dto = new ShorterURLDto("abc123", LocalDateTime.now().plusDays(10), "https://example.com/redis",
                "http://short.ly/abc123");
        redisRepo.save(dto);

        String resolved = service.resolveShortURL(dto.getShortURL());

        assertEquals(dto.getOriginalURL(), resolved);
    }

    @Test
    void resolveShortURL_fallsBackToMemoryWhenRedisMissing() {
        ShorterURLDto dto = new ShorterURLDto("def456", LocalDateTime.now().plusDays(10), "https://example.com/memory",
                "http://short.ly/def456");
        repo.save(dto);

        String resolved = service.resolveShortURL(dto.getShortURL());

        assertEquals(dto.getOriginalURL(), resolved);
    }

    @Test
    void resolveShortURL_throwsWhenShortUrlExpired() {
        ShorterURLDto dto = service.createShortURL("https://example.com/expired", LocalDateTime.now().minusDays(1));
        assertThrows(ShortURLExpiredException.class, () -> service.resolveShortURL(dto.getShortURL()));
    }

    @Test
    void createShortURL_throwsWhenOriginalUrlInvalid() {
        assertThrows(InvalidShortURLException.class, () -> service.createShortURL("invalid-url"));
    }

    @Test
    void resolveShortURL_acceptsShortKeyWithoutHost() {
        ShorterURLDto dto = service.createShortURL("https://example.com/keytest");
        String loaded = service.resolveShortURL(dto.getShortURL().replace("http://short.ly/", ""));
        assertEquals(dto.getOriginalURL(), loaded);
    }
}
