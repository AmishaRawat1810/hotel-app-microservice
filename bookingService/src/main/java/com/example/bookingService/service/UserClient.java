package com.example.bookingService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public String getUsername(String id) {

        return restClient.get()
                .uri("/api/users/username/{id}", id)
                .retrieve()
                .body(String.class);
    }

    public String getUserId(String token) {

        return restClient.get()
                .uri("api/users/me")
                .header("Authorization", token)
                .retrieve()
                .body(String.class);
    }
}