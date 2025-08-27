package com.citykart.service;

import com.citykart.dtos.SectionContentDTO;
import com.citykart.entities.SectionContent;
import com.citykart.mappers.SectionContentMapper;
import com.citykart.repos.SectionContentRepository;
import com.citykart.service.IFace.SectionContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionContentServiceImpl implements SectionContentService {

    private final SectionContentRepository repository;

    @Override
    public List<SectionContentDTO> getAll() {
        return repository.findAll().stream()
                .map(SectionContentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionContentDTO getById(Long id) {
        return repository.findById(id)
                .map(SectionContentMapper::toDto)
                .orElse(null);
    }

    @Override
    public SectionContentDTO create(SectionContentDTO dto) {
        SectionContent entity = SectionContentMapper.toEntity(dto);
        return SectionContentMapper.toDto(repository.save(entity));
    }

    @Override
    public SectionContentDTO update(Long id, SectionContentDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    SectionContentMapper.updateEntity(dto, entity);
                    return SectionContentMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}