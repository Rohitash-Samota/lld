package com.example.lld.shorter_url.exception;

public class ShortURLNotFoundException extends RuntimeException {
    public ShortURLNotFoundException(String message) {
        super(message);
    }
}
