package com.citykart.mappers;

import com.citykart.dtos.SectionDTO;
import com.citykart.dtos.SectionContentDTO;
import com.citykart.entities.Section;
import com.citykart.entities.SectionContent;
import com.citykart.entities.EntityItem;
import java.util.List;
import static com.citykart.mappers.MapperUtils.*;

public final class SectionMapper {
    private SectionMapper() {}

    public static SectionDTO toDto(Section e) {
        if (e == null) return null;
        List<SectionContentDTO> contentDtos = mapList(e.getContents(), SectionContentMapper::toDto);
        return SectionDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .name(e.getName())
                .visible(e.isVisible())
                .contents(contentDtos)
                .build();
    }

    public static Section toEntity(SectionDTO d) {
        if (d == null) return null;
        Section e = new Section();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setVisible(d.isVisible());
        // children
        List<SectionContent> contents = mapList(d.getContents(), SectionContentMapper::toEntity);
        e.setContents(contents);
        // backrefs
        for (SectionContent c : contents) SectionContentMapper.attachParent(c, e);
        return e;
    }

    public static void attachParent(Section e, EntityItem parent) {
        if (e != null) e.setEntity(parent);
    }
    
    public static void updateEntity(SectionDTO dto, Section entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getName() != null) entity.setName(dto.getName());
        entity.setVisible(dto.isVisible());
        
        // Handle contents collection if provided
        if (dto.getContents() != null) {
            List<SectionContent> contents = mapList(dto.getContents(), SectionContentMapper::toEntity);
            entity.getContents().clear();
            entity.getContents().addAll(contents);
            for (SectionContent c : contents) SectionContentMapper.attachParent(c, entity);
        }
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
