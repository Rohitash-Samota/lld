package com.example.lld.shorter_url;

import java.time.LocalDateTime;

public class ShorterURLDto {
    private String id;
    private String originalURL;
    private String shortURL;

    private LocalDateTime createdAt;
    private LocalDateTime expiryDate;

    private long clickCount;

    private boolean active;

    public ShorterURLDto(String id, LocalDateTime expiryDate, String originalURL, String shortURL) {
        this.id = id;
        this.expiryDate = expiryDate;
        this.originalURL = originalURL;
        this.shortURL = shortURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

}
