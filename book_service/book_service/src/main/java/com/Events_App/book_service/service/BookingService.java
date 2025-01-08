package com.Events_App.book_service.service;

import com.Events_App.book_service.dto.BookLineItemDto;
import com.Events_App.book_service.dto.BookingRequest;
import com.Events_App.book_service.model.BookLineItems;
import com.Events_App.book_service.model.Booking;
import com.Events_App.book_service.repository.BookingRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    public void placeBooking(BookingRequest bookingRequest)
    {
        Booking booking = new Booking();
        booking.setBookingNumber(UUID.randomUUID().toString());

        List<BookLineItems> bookLineItems = bookingRequest.getBookLineItemDtoList()
                .stream()
                .map(this::mapTDto)
                .toList();

        booking.setBookLineItemsList(bookLineItems);
        bookingRepository.save(booking);
    }

    private BookLineItems mapTDto(BookLineItemDto bookLineItemDto) {
        BookLineItems bookLineItems = new BookLineItems();
        bookLineItems.setPrice(bookLineItemDto.getPrice());
        bookLineItems.setQuantity(bookLineItemDto.getQuantity());
        bookLineItems.setSkuCode(bookLineItemDto.getSkuCode());
        return bookLineItems;
    }
}
