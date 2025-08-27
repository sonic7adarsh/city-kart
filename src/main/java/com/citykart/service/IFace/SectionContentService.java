package com.citykart.service.IFace;

import com.citykart.dtos.SectionContentDTO;
import java.util.List;

public interface SectionContentService {
    List<SectionContentDTO> getAll();
    SectionContentDTO getById(Long id);
    SectionContentDTO create(SectionContentDTO dto);
    SectionContentDTO update(Long id, SectionContentDTO dto);
    void delete(Long id);
}