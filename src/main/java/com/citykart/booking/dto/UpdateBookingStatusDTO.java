package com.citykart.booking.dto;

import com.citykart.booking.enums.BookingStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingStatusDTO {
    private BookingStatus status;
}
