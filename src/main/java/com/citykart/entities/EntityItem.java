package com.citykart.entities;

import com.citykart.booking.entity.Booking;
import com.citykart.entities.abs.BaseEntity;
import com.citykart.entities.enums.EntityType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entity_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityItem extends BaseEntity {

    private String name;
    private String slug;

    @Enumerated(EnumType.STRING)
    private EntityType type; // HOTEL, RESTAURANT, EVENT, STORE

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntityProduct> products = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Testimonial> testimonials = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookItem> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactInfo> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialLink> socialLinks = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Amenity> amenities = new ArrayList<>();
}
