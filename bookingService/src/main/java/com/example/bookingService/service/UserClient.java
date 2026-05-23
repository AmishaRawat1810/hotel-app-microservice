package com.example.bookingService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient.Builder builder) {

        this.restClient = builder
//                .baseUrl("http://localhost:8081")
                .baseUrl("http://10.132.124.232:3000")
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
                .uri("api/users/me{token}", token)
                .retrieve()
                .body(String.class);
    }
}