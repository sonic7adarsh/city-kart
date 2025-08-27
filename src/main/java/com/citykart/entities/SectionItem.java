package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "section_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionItem extends BaseEntity {

    private String label;       // e.g. Rating, Location, Icon title
    private String value;       // e.g. "4.8/5", "New Delhi"
    private String icon;        // e.g. FaStar, FaMapMarkerAlt
    private String imageUrl;    // for gallery
    private String description; // optional

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}

