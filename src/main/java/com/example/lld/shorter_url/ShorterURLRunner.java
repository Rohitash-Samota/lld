package com.example.lld.shorter_url;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ShorterURLRunner implements CommandLineRunner {

    private final ShorterURLService shorterURLService;

    public ShorterURLRunner(ShorterURLService shorterURLService) {
        this.shorterURLService = shorterURLService;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Short URL Demo ===");

        String originalURL = "https://example.com/products/12345?ref=home";
        ShorterURLDto mapping = shorterURLService.createShortURL(originalURL, LocalDateTime.now().plusDays(7));

        System.out.println("Original URL: " + mapping.getOriginalURL());
        System.out.println("Short URL: " + mapping.getShortURL());
        System.out.println("Expiry Date: " + mapping.getExpiryDate());

        String resolved = shorterURLService.resolveShortURL(mapping.getShortURL());
        System.out.println("Resolved URL: " + resolved);
    }
}
