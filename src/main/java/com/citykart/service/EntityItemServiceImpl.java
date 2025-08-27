package com.citykart.service;

import com.citykart.dtos.EntityItemDTO;
import com.citykart.entities.EntityItem;
import com.citykart.mappers.EntityItemMapper;
import com.citykart.repos.EntityItemRepository;
import com.citykart.service.IFace.EntityItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EntityItemServiceImpl implements EntityItemService {

    private final EntityItemRepository repository;

    @Override
    public List<EntityItemDTO> getAll() {
        return repository.findAll().stream()
                .map(EntityItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntityItemDTO getById(Long id) {
        return repository.findById(id)
                .map(EntityItemMapper::toDto)
                .orElse(null);
    }

    @Override
    public EntityItemDTO create(EntityItemDTO dto) {
        EntityItem entity = EntityItemMapper.toEntity(dto);
        return EntityItemMapper.toDto(repository.save(entity));
    }

    @Override
    public EntityItemDTO update(Long id, EntityItemDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    EntityItemMapper.updateEntity(dto, entity);
                    return EntityItemMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
