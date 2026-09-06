package com.project.url_shortener.service;

import com.project.url_shortener.dto.UrlRequest;
import com.project.url_shortener.dto.UrlResponse;

public interface UrlShortenerService {
    UrlResponse shortenUrl(UrlRequest urlRequest);
    String getOriginalUrl(String shortCode);
}
