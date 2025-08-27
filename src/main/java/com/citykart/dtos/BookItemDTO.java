package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookItemDTO extends BaseDTO {
    private String userId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int guests;
    private double totalPrice;
    private String status;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String specialRequests;

    private Long entityId;
    private Long productId;
}

