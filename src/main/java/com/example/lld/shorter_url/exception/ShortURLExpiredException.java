package com.example.lld.shorter_url.exception;

public class ShortURLExpiredException extends RuntimeException {
    public ShortURLExpiredException(String message) {
        super(message);
    }
}
