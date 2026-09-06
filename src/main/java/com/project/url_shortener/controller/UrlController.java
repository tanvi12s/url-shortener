package com.project.url_shortener.controller;

import com.project.url_shortener.dto.UrlRequest;
import com.project.url_shortener.dto.UrlResponse;
import com.project.url_shortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    private final UrlShortenerService urlService;

    public UrlController(UrlShortenerService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/urls")
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody UrlRequest urlRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(urlService.shortenUrl(urlRequest));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shortCode) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(urlService.getOriginalUrl(shortCode)))
                .build();
    }
}
