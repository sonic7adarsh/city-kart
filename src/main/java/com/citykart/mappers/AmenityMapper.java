package com.citykart.mappers;

import com.citykart.dtos.AmenityDTO;
import com.citykart.entities.Amenity;
import com.citykart.entities.EntityItem;

public final class AmenityMapper {
    private AmenityMapper() {}

    public static AmenityDTO toDto(Amenity e) {
        if (e == null) return null;
        AmenityDTO d = AmenityDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .name(e.getName())
                .icon(e.getIcon())
                .build();
        return d;
    }

    public static Amenity toEntity(AmenityDTO d) {
        if (d == null) return null;
        Amenity e = new Amenity();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setIcon(d.getIcon());
        return e;
    }

    public static void attachParent(Amenity e, EntityItem parent) {
        if (e != null) e.setEntity(parent);
    }

    public static void updateEntity(AmenityDTO dto, Amenity entity) {
        if (dto == null || entity == null) return;

        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getIcon() != null) entity.setIcon(dto.getIcon());

        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
        // id aur createdAt generally update nahi hote, isliye unko skip karte hain
    }
}
