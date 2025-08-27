package com.citykart.service.IFace;

import com.citykart.dtos.SocialLinkDTO;
import java.util.List;

public interface SocialLinkService {
    List<SocialLinkDTO> getAll();
    SocialLinkDTO getById(Long id);
    SocialLinkDTO create(SocialLinkDTO dto);
    SocialLinkDTO update(Long id, SocialLinkDTO dto);
    void delete(Long id);
}