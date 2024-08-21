package com.ohgiraffers.midnightback.service;

import com.ohgiraffers.midnightback.dto.ExternalApiRequestDto;
import com.ohgiraffers.midnightback.dto.ExternalApiResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalApiService {

    private final String apiUrl = "http://192.168.1.84:8000/chat/sample";
    private final RestTemplate restTemplate;

    @Autowired
    public ExternalApiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Async
    public CompletableFuture<ExternalApiResponseDto> sendRequest(ExternalApiRequestDto requestDto) {
        ResponseEntity<ExternalApiResponseDto> response = restTemplate.postForEntity(apiUrl, requestDto, ExternalApiResponseDto.class);
        return CompletableFuture.completedFuture(response.getBody());
    }
}
