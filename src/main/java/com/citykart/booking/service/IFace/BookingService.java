package com.citykart.booking.service.IFace;

import com.citykart.booking.dto.BookingDTO;
import com.citykart.booking.entity.Booking;
import com.citykart.booking.enums.BookingStatus;

import java.util.List;

public interface BookingService {

    Booking createBooking(BookingDTO dto);
    List<Booking> getMyBookings();
    Booking updateStatus(Long id, BookingStatus status);
}
