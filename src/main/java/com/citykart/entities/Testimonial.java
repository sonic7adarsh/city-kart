package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testimonial extends BaseEntity {

    private String guestName;
    private int rating;
    private LocalDate date;
    private String comment;
    private String location;
    private String productName; // e.g. RoomType, DishName
    private boolean verified;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;
}