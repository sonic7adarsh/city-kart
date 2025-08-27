package com.citykart.service;

import com.citykart.dtos.EntityProductDTO;
import com.citykart.entities.EntityProduct;
import com.citykart.mappers.EntityProductMapper;
import com.citykart.repos.EntityProductRepository;
import com.citykart.service.IFace.EntityProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntityProductServiceImpl implements EntityProductService {

    private final EntityProductRepository repository;

    @Override
    public List<EntityProductDTO> getAll() {
        return repository.findAll().stream()
                .map(EntityProductMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntityProductDTO getById(Long id) {
        return repository.findById(id)
                .map(EntityProductMapper::toDto)
                .orElse(null);
    }

    @Override
    public EntityProductDTO create(EntityProductDTO dto) {
        EntityProduct entity = EntityProductMapper.toEntity(dto);
        return EntityProductMapper.toDto(repository.save(entity));
    }

    @Override
    public EntityProductDTO update(Long id, EntityProductDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    EntityProductMapper.updateEntity(dto, entity);
                    return EntityProductMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}