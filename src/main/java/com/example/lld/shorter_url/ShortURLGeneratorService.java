package com.example.lld.shorter_url;

import org.springframework.stereotype.Service;

@Service("shortURLGeneratorService")
public class ShortURLGeneratorService {

    // Take a Array for all char and digits
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
            System.err.println(encoded);
        }
        System.err.println("Last code " + encoded);
        return encoded.reverse().toString();
    }

    public String nextId() {
        return generateId();
    }
}
