package com.citykart.mappers;

import com.citykart.dtos.SectionContentDTO;
import com.citykart.entities.SectionContent;
import com.citykart.entities.Section;

public final class SectionContentMapper {
    private SectionContentMapper() {}

    public static SectionContentDTO toDto(SectionContent e) {
        if (e == null) return null;
        return SectionContentDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .title(e.getTitle())
                .subtitle(e.getSubtitle())
                .description(e.getDescription())
                .icon(e.getIcon())
                .imageUrl(e.getImageUrl())
                .build();
    }

    public static SectionContent toEntity(SectionContentDTO d) {
        if (d == null) return null;
        SectionContent e = new SectionContent();
        e.setId(d.getId());
        e.setTitle(d.getTitle());
        e.setSubtitle(d.getSubtitle());
        e.setDescription(d.getDescription());
        e.setIcon(d.getIcon());
        e.setImageUrl(d.getImageUrl());
        return e;
    }

    public static void attachParent(SectionContent e, Section parent) {
        if (e != null) e.setSection(parent);
    }
    
    public static void updateEntity(SectionContentDTO dto, SectionContent entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getTitle() != null) entity.setTitle(dto.getTitle());
        if (dto.getSubtitle() != null) entity.setSubtitle(dto.getSubtitle());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getIcon() != null) entity.setIcon(dto.getIcon());
        if (dto.getImageUrl() != null) entity.setImageUrl(dto.getImageUrl());
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
