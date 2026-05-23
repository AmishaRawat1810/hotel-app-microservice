package com.example.bookingService.requestDTO;

public record UpdateRoomsRequestDTO(
        String hotelId,
        Integer rooms
) {}
