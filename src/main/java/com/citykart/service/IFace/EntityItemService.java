package com.citykart.service.IFace;


import com.citykart.dtos.EntityItemDTO;
import java.util.List;

public interface EntityItemService {
    List<EntityItemDTO> getAll();
    EntityItemDTO getById(Long id);
    EntityItemDTO create(EntityItemDTO dto);
    EntityItemDTO update(Long id, EntityItemDTO dto);
    void delete(Long id);
}
