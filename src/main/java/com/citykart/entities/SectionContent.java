package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "section_contents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionContent extends BaseEntity {

    private String title;
    private String subtitle;
    private String description;
    private String icon;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}

