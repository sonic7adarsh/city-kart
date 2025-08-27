package com.citykart.service;

import com.citykart.dtos.AmenityDTO;
import com.citykart.entities.Amenity;
import com.citykart.mappers.AmenityMapper;
import com.citykart.repos.AmenityRepository;
import com.citykart.service.IFace.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository repository;

    @Override
    public List<AmenityDTO> getAll() {
        return repository.findAll().stream()
                .map(AmenityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AmenityDTO getById(Long id) {
        return repository.findById(id)
                .map(AmenityMapper::toDto)
                .orElse(null);
    }

    @Override
    public AmenityDTO create(AmenityDTO dto) {
        Amenity entity = AmenityMapper.toEntity(dto);
        return AmenityMapper.toDto(repository.save(entity));
    }

    @Override
    public AmenityDTO update(Long id, AmenityDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    AmenityMapper.updateEntity(dto, entity);
                    return AmenityMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
