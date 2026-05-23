package com.example.bookingService.controller;

import com.example.bookingService.model.Booking;
import com.example.bookingService.requestDTO.BookHotelRequestDTO;
import com.example.bookingService.service.BookingService;
import com.example.bookingService.service.SearchClient;
import com.example.bookingService.service.UserClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;
    private final UserClient userClient;

    public BookingController(BookingService bookingService, UserClient userClient) {
        this.bookingService = bookingService;
        this.userClient = userClient;
    }


    @PostMapping("/bookings")
    public ResponseEntity<Map<String, String>> bookHotelRooms(@RequestHeader(value = "Authorization", required = false) String authHeader  ,
                                                              @RequestBody BookHotelRequestDTO bookHotelRequestDTO ) {
        try{
            String username = "default user"; // later we'll extract this using jwt token
            String userID = "0" ; // extract userid from username

            this.bookingService.bookRooms(userID, bookHotelRequestDTO);

            return ResponseEntity.ok(Map.of("message", "Booking successful"));
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("message" , e.getMessage()));
        }
    }

    @GetMapping("/bookings")
    public List<Booking> getBookings(@RequestHeader(value = "Authorization") String authHeader) {

        String token = authHeader.substring(7);
//        String userID = "0" ;
        String userID = userClient.getUserId(token);
        return bookingService.getBookings(userID);

    }

    @GetMapping("/bookings/{id}/receipt.pdf")
    public ResponseEntity<?> getReceipt(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=receipt.pdf");
        ByteArrayInputStream pdfStream = bookingService.generateReceiptPdf(id);

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }
}
