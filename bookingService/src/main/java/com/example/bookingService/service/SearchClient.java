package com.example.bookingService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.example.bookingService.requestDTO.UpdateRoomsRequestDTO;
@Service
public class SearchClient {

    private final RestClient restClient;

    public SearchClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://localhost:8000")
                .build();
    }

    public boolean checkAvailability(
            String hotelId,
            Integer rooms) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/availability")
                        .queryParam("rooms", rooms)
                        .queryParam("id", hotelId)
                        .build())
                .retrieve()
                .body(Boolean.class);
    }

    public void updateRoomsAvailable(
            String hotelId,
            Integer rooms) {

        UpdateRoomsRequestDTO request =
                new UpdateRoomsRequestDTO(
                        hotelId,
                        rooms);

        restClient.post()
                .uri("/search/update-rooms")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    public String getHotelName(String id) {

        return restClient.get()
                .uri("/hotels/{id}", id)
                .retrieve()
                .body(String.class);
    }
}