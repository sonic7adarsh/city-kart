package com.citykart.booking.controller;

import com.citykart.booking.dto.BookingDTO;
import com.citykart.booking.dto.UpdateBookingStatusDTO;
import com.citykart.booking.entity.Booking;
import com.citykart.booking.service.IFace.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public Booking create(@RequestBody BookingDTO dto) {
        return service.createBooking(dto);
    }

    @GetMapping("/my")
    public List<Booking> getMyBookings() {
        return service.getMyBookings();
    }

    @PutMapping("/{id}/status")
    public Booking updateStatus(@PathVariable Long id,
                                @RequestBody UpdateBookingStatusDTO dto) {
        return service.updateStatus(id, dto.getStatus());
    }
}
