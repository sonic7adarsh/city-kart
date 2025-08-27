package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "amenities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Amenity extends BaseEntity {

    private String name;
    private String icon;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;
}
