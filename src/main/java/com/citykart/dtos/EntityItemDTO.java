package com.citykart.dtos;

import com.citykart.entities.enums.EntityType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EntityItemDTO extends BaseDTO {
    private String name;
    private String slug;
    private EntityType type; // HOTEL, RESTAURANT, EVENT, STORE
    private List<EntityProductDTO> products;
    private List<SectionDTO> sections;
    private List<TestimonialDTO> testimonials;
    private List<BookItemDTO> bookings;
    private List<ContactInfoDTO> contacts;
    private List<SocialLinkDTO> socialLinks;
    private List<AmenityDTO> amenities;
}

