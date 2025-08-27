package com.citykart.mappers;

import com.citykart.dtos.ContactInfoDTO;
import com.citykart.entities.ContactInfo;
import com.citykart.entities.EntityItem;

public final class ContactInfoMapper {
    private ContactInfoMapper() {}

    public static ContactInfoDTO toDto(ContactInfo e) {
        if (e == null) return null;
        return ContactInfoDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .label(e.getLabel())
                .value(e.getValue())
                .icon(e.getIcon())
                .build();
    }

    public static ContactInfo toEntity(ContactInfoDTO d) {
        if (d == null) return null;
        ContactInfo e = new ContactInfo();
        e.setId(d.getId());
        e.setLabel(d.getLabel());
        e.setValue(d.getValue());
        e.setIcon(d.getIcon());
        return e;
    }

    public static void attachParent(ContactInfo e, EntityItem parent) {
        if (e != null) e.setEntity(parent);
    }
    
    public static void updateEntity(ContactInfoDTO dto, ContactInfo entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getLabel() != null) entity.setLabel(dto.getLabel());
        if (dto.getValue() != null) entity.setValue(dto.getValue());
        if (dto.getIcon() != null) entity.setIcon(dto.getIcon());
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
