package com.citykart.entities;

import jakarta.persistence.Entity;
import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ContactInfo extends BaseEntity {

    private String label;
    private String value;
    private String icon;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;
}


