package com.citykart.service;

import com.citykart.dtos.SocialLinkDTO;
import com.citykart.entities.SocialLink;
import com.citykart.mappers.SocialLinkMapper;
import com.citykart.repos.SocialLinkRepository;
import com.citykart.service.IFace.SocialLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialLinkServiceImpl implements SocialLinkService {

    private final SocialLinkRepository repository;

    @Override
    public List<SocialLinkDTO> getAll() {
        return repository.findAll().stream()
                .map(SocialLinkMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SocialLinkDTO getById(Long id) {
        return repository.findById(id)
                .map(SocialLinkMapper::toDto)
                .orElse(null);
    }

    @Override
    public SocialLinkDTO create(SocialLinkDTO dto) {
        SocialLink entity = SocialLinkMapper.toEntity(dto);
        return SocialLinkMapper.toDto(repository.save(entity));
    }

    @Override
    public SocialLinkDTO update(Long id, SocialLinkDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    SocialLinkMapper.updateEntity(dto, entity);
                    return SocialLinkMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}