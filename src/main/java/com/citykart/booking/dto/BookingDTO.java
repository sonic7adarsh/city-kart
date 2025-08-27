package com.citykart.booking.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO {
    private String customerName;
    private String customerPhone;
    private Long productId;
}
