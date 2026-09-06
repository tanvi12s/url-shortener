package com.project.url_shortener.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;

    @Column(name = "short_code", unique = true)
    private String shortCode;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
