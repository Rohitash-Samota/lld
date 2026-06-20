package com.example.lld.shorter_url;

import org.springframework.stereotype.Service;

@Service
public class ShortURLGeneratorService {

    private static final char[] ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
            .toCharArray();
    private long nextId = 1000L;

    public synchronized String generateId() {
        return encodeBase62(nextId++);
    }

    private String encodeBase62(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive");
        }

        StringBuilder encoded = new StringBuilder();
        while (value > 0) {
            encoded.append(ALPHABET[(int) (value % ALPHABET.length)]);
            value /= ALPHABET.length;
        }

        return encoded.reverse().toString();
    }

    public String nextId() {
        return generateId();
    }
}
