package com.project.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UrlRequest {
    @NotBlank(message = "URL is required")
    @URL(message = "Invalid URL format")
    private String url;
}
