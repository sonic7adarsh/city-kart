package com.citykart.service;

import com.citykart.dtos.ContactInfoDTO;
import com.citykart.entities.ContactInfo;
import com.citykart.mappers.ContactInfoMapper;
import com.citykart.repos.ContactInfoRepository;
import com.citykart.service.IFace.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository repository;

    @Override
    public List<ContactInfoDTO> getAll() {
        return repository.findAll().stream()
                .map(ContactInfoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContactInfoDTO getById(Long id) {
        return repository.findById(id)
                .map(ContactInfoMapper::toDto)
                .orElse(null);
    }

    @Override
    public ContactInfoDTO create(ContactInfoDTO dto) {
        ContactInfo entity = ContactInfoMapper.toEntity(dto);
        return ContactInfoMapper.toDto(repository.save(entity));
    }

    @Override
    public ContactInfoDTO update(Long id, ContactInfoDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    ContactInfoMapper.updateEntity(dto, entity);
                    return ContactInfoMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}