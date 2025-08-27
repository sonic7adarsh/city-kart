package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entity_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntityProduct extends BaseEntity {

    private String name;
    private String type;       // ROOM, DISH, TICKET, ITEM
    private String category;
    private String description;
    private double price;
    private int maxGuests;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "product_features", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "feature")
    private List<String> features = new ArrayList<>(); // amenities/ingredients/etc
}
