package com.citykart.reviews.entity;

import com.citykart.product.entity.Product;
import com.citykart.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "product"})
@EqualsAndHashCode(exclude = {"user", "product"})
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    // ✅ Reviewer (User with Role = CUSTOMER)
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ Product being reviewed
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // ✅ Rating + Comments
    private int rating; // 1 to 5
    private String comment;

    private LocalDateTime createdAt;
}
