package com.example.bookingService.repository;

import com.example.bookingService.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> getBookingsByUserID(String userID);

    Booking getBookingById(String id);
}
