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
        this.active = true;
        this.clickCount = 0;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getClickCount() {
        return clickCount;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
