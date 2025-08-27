package com.citykart.service.IFace;

import com.citykart.dtos.SectionDTO;
import java.util.List;

public interface SectionService {
    List<SectionDTO> getAll();
    SectionDTO getById(Long id);
    SectionDTO create(SectionDTO dto);
    SectionDTO update(Long id, SectionDTO dto);
    void delete(Long id);
}