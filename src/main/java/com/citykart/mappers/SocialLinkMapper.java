package com.citykart.mappers;

import com.citykart.dtos.SocialLinkDTO;
import com.citykart.entities.SocialLink;
import com.citykart.entities.EntityItem;

public final class SocialLinkMapper {
    private SocialLinkMapper() {}

    public static SocialLinkDTO toDto(SocialLink e) {
        if (e == null) return null;
        return SocialLinkDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .platform(e.getPlatform())
                .url(e.getUrl())
                .icon(e.getIcon())
                .build();
    }

    public static SocialLink toEntity(SocialLinkDTO d) {
        if (d == null) return null;
        SocialLink e = new SocialLink();
        e.setId(d.getId());
        e.setPlatform(d.getPlatform());
        e.setUrl(d.getUrl());
        e.setIcon(d.getIcon());
        return e;
    }

    public static void attachParent(SocialLink e, EntityItem parent) {
        if (e != null) e.setEntity(parent);
    }
    
    public static void updateEntity(SocialLinkDTO dto, SocialLink entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getPlatform() != null) entity.setPlatform(dto.getPlatform());
        if (dto.getUrl() != null) entity.setUrl(dto.getUrl());
        if (dto.getIcon() != null) entity.setIcon(dto.getIcon());
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
