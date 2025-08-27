package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TestimonialDTO extends BaseDTO {
    private String guestName;
    private int rating;
    private LocalDate date;
    private String comment;
    private String location;
    private String productName;
    private boolean verified;
}

