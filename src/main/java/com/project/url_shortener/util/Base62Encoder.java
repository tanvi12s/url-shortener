package com.project.url_shortener.util;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public final class Base62Encoder {

    private static final char[] ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final int BASE = 62;
    
    public String encode(Long id) {
        Objects.requireNonNull(id, "ID cannot be null");
        if(id == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while(id > 0) {
            int rem = (int) (id % BASE);
            sb.append(ALPHABET[rem]);
            id /= BASE;
        }
        return sb.reverse().toString();
    }
}
