package com.citykart.user.dto;

import com.citykart.reviews.dto.ReviewDTO;
import com.citykart.user.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String phone;
    private String email;
    private String role; // CUSTOMER / VENDOR / ADMIN
    private boolean active;
    private String city;
    private String category;
    private String address;
    private String subscriptionPlan; // BASIC, PRO, PREMIUM
    private Long subscriptionExpiry; // Timestamp in millis
    private LocalDateTime createdAt;
    private List<ReviewDTO> reviews;
    private Double averageRating;
}
