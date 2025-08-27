package com.citykart.booking.entity;

import com.citykart.booking.enums.BookingStatus;
import com.citykart.product.entity.Product;
import com.citykart.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerPhone;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private BookingStatus status; //

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;
}
