package com.citykart.mappers;

import com.citykart.dtos.TestimonialDTO;
import com.citykart.entities.Testimonial;
import com.citykart.entities.EntityItem;

public final class TestimonialMapper {
    private TestimonialMapper() {}

    public static TestimonialDTO toDto(Testimonial e) {
        if (e == null) return null;
        return TestimonialDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .guestName(e.getGuestName())
                .rating(e.getRating())
                .date(e.getDate())
                .comment(e.getComment())
                .location(e.getLocation())
                .productName(e.getProductName())
                .verified(e.isVerified())
                .build();
    }

    public static Testimonial toEntity(TestimonialDTO d) {
        if (d == null) return null;
        Testimonial e = new Testimonial();
        e.setId(d.getId());
        e.setGuestName(d.getGuestName());
        // Ensure rating is valid (between 1-5 typically)
        e.setRating(Math.max(1, Math.min(5, d.getRating())));
        e.setDate(d.getDate());
        e.setComment(d.getComment());
        e.setLocation(d.getLocation());
        e.setProductName(d.getProductName());
        e.setVerified(d.isVerified());
        return e;
    }

    public static void attachParent(Testimonial e, EntityItem parent) {
        if (e != null) e.setEntity(parent);
    }
    
    public static void updateEntity(TestimonialDTO dto, Testimonial entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getGuestName() != null) entity.setGuestName(dto.getGuestName());
        if (dto.getRating() > 0) {
            // Ensure rating is valid (between 1-5 typically)
            entity.setRating(Math.max(1, Math.min(5, dto.getRating())));
        }
        if (dto.getDate() != null) entity.setDate(dto.getDate());
        if (dto.getComment() != null) entity.setComment(dto.getComment());
        if (dto.getLocation() != null) entity.setLocation(dto.getLocation());
        if (dto.getProductName() != null) entity.setProductName(dto.getProductName());
        // Boolean fields don't need null check as they're primitives
        entity.setVerified(dto.isVerified());
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
