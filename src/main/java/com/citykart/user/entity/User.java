package com.citykart.user.entity;

import com.citykart.reviews.entity.Review;
import com.citykart.user.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role; // CUSTOMER / VENDOR / ADMIN
    private boolean active;
    private String city;
    private String category;
    private String address;
    private String subscriptionPlan; // BASIC, PRO, PREMIUM
    private Long subscriptionExpiry; // Timestamp in millis
    private LocalDateTime createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    private List<Review> myReviews;
}

