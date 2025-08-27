package com.citykart.service.IFace;

import com.citykart.dtos.EntityProductDTO;
import java.util.List;

public interface EntityProductService {
    List<EntityProductDTO> getAll();
    EntityProductDTO getById(Long id);
    EntityProductDTO create(EntityProductDTO dto);
    EntityProductDTO update(Long id, EntityProductDTO dto);
    void delete(Long id);
}