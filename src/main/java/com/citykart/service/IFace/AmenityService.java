package com.citykart.service.IFace;


import com.citykart.dtos.AmenityDTO;
import java.util.List;

public interface AmenityService {
    List<AmenityDTO> getAll();
    AmenityDTO getById(Long id);
    AmenityDTO create(AmenityDTO dto);
    AmenityDTO update(Long id, AmenityDTO dto);
    void delete(Long id);
}
