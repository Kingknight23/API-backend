package com.Events_App.book_service.controller;

import com.Events_App.book_service.dto.BookingRequest;
import com.Events_App.book_service.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeBooking(@RequestBody BookingRequest bookingRequest){

        bookingService.placeBooking(bookingRequest);

        return "Event Booked";
    }

}
