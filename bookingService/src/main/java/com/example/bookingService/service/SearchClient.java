package com.example.bookingService.service;

import com.example.bookingService.requestDTO.HotelNameResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import com.example.bookingService.requestDTO.UpdateRoomsRequestDTO;
@Service
public class SearchClient {

    private final RestClient restClient;

    public SearchClient(RestClient.Builder builder) {

        this.restClient = builder
                .baseUrl("http://search_service:3000")
                .build();
    }

    public boolean checkAvailability(
            String hotelId,
            Integer rooms) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("api/search/internal/availability")
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
                .uri("/api/search/internal/update-rooms")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }

    public String getHotelName(String id) {

        HotelNameResponse response =  restClient.get()
                .uri("api/search/internal/hotels/{id}", id)
                .retrieve()
                .body(HotelNameResponse.class);

        return response.name();
    }
}