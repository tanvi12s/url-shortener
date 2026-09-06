package com.project.url_shortener.service.impl;

import com.project.url_shortener.dto.UrlRequest;
import com.project.url_shortener.dto.UrlResponse;
import com.project.url_shortener.exception.UrlNotFoundException;
import com.project.url_shortener.model.Url;
import com.project.url_shortener.repository.UrlRepository;
import com.project.url_shortener.service.UrlShortenerService;
import com.project.url_shortener.util.Base62Encoder;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {

    private final UrlRepository urlRepository;
    private final Base62Encoder base62Encoder;

    public UrlShortenerServiceImpl(UrlRepository urlRepository, Base62Encoder base62Encoder) {
        this.urlRepository = urlRepository;
        this.base62Encoder = base62Encoder;
    }

    @Override
    public UrlResponse shortenUrl(UrlRequest urlRequest) {
        String originalUrl = urlRequest.getUrl();
        UrlResponse response = new UrlResponse();

        //adding new row to the database
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setCreatedAt(LocalDateTime.now());
        Url urlEntity = urlRepository.save(url);
        String shortCode = base62Encoder.encode(urlEntity.getId());

        //updating the shortCode in the database
        urlEntity.setShortCode(shortCode);
        //urlRepository.save(urlEntity);

        //update the response object with the short URL
        response.setShortUrl(shortCode);
        return response;
    }

    @Override
    public String getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));
    }
}
