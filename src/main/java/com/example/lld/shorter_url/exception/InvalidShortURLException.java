package com.example.lld.shorter_url.exception;

public class InvalidShortURLException extends RuntimeException {
    public InvalidShortURLException(String message) {
        super(message);
    }
}
