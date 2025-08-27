package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "book_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItem extends BaseEntity {

    private String userId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int guests;
    private double totalPrice;
    private String status; // confirmed, pending, cancelled
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String specialRequests;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private EntityProduct product;
}

