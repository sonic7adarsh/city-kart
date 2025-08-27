package com.citykart.entities;

import com.citykart.entities.abs.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social_links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialLink extends BaseEntity {

    private String platform;
    private String url;
    private String icon;

    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityItem entity;
}
