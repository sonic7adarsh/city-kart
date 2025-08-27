package com.citykart.service.IFace;

import com.citykart.dtos.ContactInfoDTO;
import java.util.List;

public interface ContactInfoService {
    List<ContactInfoDTO> getAll();
    ContactInfoDTO getById(Long id);
    ContactInfoDTO create(ContactInfoDTO dto);
    ContactInfoDTO update(Long id, ContactInfoDTO dto);
    void delete(Long id);
}