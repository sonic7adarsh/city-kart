package com.citykart.mappers;

import com.citykart.dtos.EntityProductDTO;
import com.citykart.entities.EntityItem;
import com.citykart.entities.EntityProduct;
import java.util.List;
import static com.citykart.mappers.MapperUtils.*;

public final class EntityProductMapper {
    private EntityProductMapper() {}

    public static EntityProductDTO toDto(EntityProduct e) {
        if (e == null) return null;
        return EntityProductDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .name(e.getName())
                .type(e.getType())
                .category(e.getCategory())
                .description(e.getDescription())
                .price(e.getPrice())
                .maxGuests(e.getMaxGuests())
                .available(e.isAvailable())
                .images(nullSafe(e.getImages()))
                .features(nullSafe(e.getFeatures()))
                .build();
    }

    public static EntityProduct toEntity(EntityProductDTO d) {
        if (d == null) return null;
        EntityProduct e = new EntityProduct();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setType(d.getType());
        e.setCategory(d.getCategory());
        e.setDescription(d.getDescription());
        e.setPrice(d.getPrice());
        e.setMaxGuests(d.getMaxGuests());
        e.setAvailable(d.isAvailable());
        e.setImages(nullSafe(d.getImages()));
        e.setFeatures(nullSafe(d.getFeatures()));
        return e;
    }

    public static void attachParent(EntityProduct e, EntityItem parent) {
        if (e != null) e.setEntity(parent);
    }
    
    public static void updateEntity(EntityProductDTO dto, EntityProduct entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getType() != null) entity.setType(dto.getType());
        if (dto.getCategory() != null) entity.setCategory(dto.getCategory());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getMaxGuests() != null) entity.setMaxGuests(dto.getMaxGuests());
        entity.setAvailable(dto.isAvailable());
        if (dto.getImages() != null) entity.setImages(nullSafe(dto.getImages()));
        if (dto.getFeatures() != null) entity.setFeatures(nullSafe(dto.getFeatures()));
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
