package com.citykart.service;

import com.citykart.dtos.SectionDTO;
import com.citykart.entities.Section;
import com.citykart.mappers.SectionMapper;
import com.citykart.repos.SectionRepository;
import com.citykart.service.IFace.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository repository;

    @Override
    public List<SectionDTO> getAll() {
        return repository.findAll().stream()
                .map(SectionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionDTO getById(Long id) {
        return repository.findById(id)
                .map(SectionMapper::toDto)
                .orElse(null);
    }

    @Override
    public SectionDTO create(SectionDTO dto) {
        Section entity = SectionMapper.toEntity(dto);
        return SectionMapper.toDto(repository.save(entity));
    }

    @Override
    public SectionDTO update(Long id, SectionDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    SectionMapper.updateEntity(dto, entity);
                    return SectionMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}