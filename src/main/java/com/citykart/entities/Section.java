package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section extends BaseEntity {

    private String name; // hero, about, features, gallery, etc
    private boolean visible;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionContent> contents = new ArrayList<>();
}

