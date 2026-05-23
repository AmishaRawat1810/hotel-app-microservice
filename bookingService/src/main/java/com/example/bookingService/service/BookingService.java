package com.example.bookingService.service;

import com.example.bookingService.model.Booking;
import com.example.bookingService.repository.BookingRepository;

import com.example.bookingService.requestDTO.BookHotelRequestDTO;
import com.example.bookingService.responseDTO.BookingResponseDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final SearchClient searchClient;

    public BookingService(SearchClient searchClient, BookingRepository bookingRepository,  UserClient userClient ) {
        this.searchClient = searchClient;
        this.bookingRepository = bookingRepository;
        this.userClient = userClient;
    }

    public void bookRooms(String userID, BookHotelRequestDTO bookHotelRequestDTO) throws  RuntimeException{

//        Boolean areRoomsAvailable = searchClient.checkAvailability(bookHotelRequestDTO.hotel_id(), bookHotelRequestDTO.rooms());
        Boolean areRoomsAvailable = true;
        if (areRoomsAvailable) {
            Booking booking = new Booking(null, userID, bookHotelRequestDTO.hotel_id(), bookHotelRequestDTO.rooms());
//            searchClient.updateRoomsAvailable(bookHotelRequestDTO.hotel_id(), bookHotelRequestDTO.rooms());

            bookingRepository.save(booking);
            return;
        }

        throw  new RuntimeException("those many rooms are not left in this hotel");
    }

    private BookingResponseDTO mapToDTO(Booking booking) {

        return new BookingResponseDTO(
                booking.id(),
                booking.hotelID(),
                booking.noOfRoomsBooked()
        );
    }


    public ByteArrayInputStream generateReceiptPdf(String id) {
        Booking booking = bookingRepository.getBookingById(id);
//        String username = userClient.getUsername(booking.userID());
//        String hotelName = searchClient.getHotelName(booking.hotelID());
        String username = "hello";
        String hotelName = "hotel 1";
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            generateDocument(document, booking, username, hotelName);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error creating the pdf file", e);
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void generateDocument(Document document, Booking booking, String username, String hotelName) {
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Paragraph title = new Paragraph("BOOKING RECEIPT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        document.add(new Paragraph("Booking ID: " + booking.id(), bodyFont));
        document.add(new Paragraph("User: " + username, bodyFont));
        document.add(new Paragraph("Hotel: " + hotelName, bodyFont));
        document.add(new Paragraph("Rooms Booked: " + booking.noOfRoomsBooked(),
                bodyFont));
    }

    public List<Booking> getBookings(String userID) {
        return bookingRepository.getBookingsByUserID(userID);
    }
}
