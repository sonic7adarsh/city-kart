package com.citykart.reviews.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {
    private Long id;
    private Long userId;
    private String userName;
    private int rating;
    private String comment;
}
