package com.citykart.service;

import com.citykart.dtos.TestimonialDTO;
import com.citykart.entities.Testimonial;
import com.citykart.mappers.TestimonialMapper;
import com.citykart.repos.TestimonialRepository;
import com.citykart.service.IFace.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository repository;

    @Override
    public List<TestimonialDTO> getAll() {
        return repository.findAll().stream()
                .map(TestimonialMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TestimonialDTO getById(Long id) {
        return repository.findById(id)
                .map(TestimonialMapper::toDto)
                .orElse(null);
    }

    @Override
    public TestimonialDTO create(TestimonialDTO dto) {
        Testimonial entity = TestimonialMapper.toEntity(dto);
        return TestimonialMapper.toDto(repository.save(entity));
    }

    @Override
    public TestimonialDTO update(Long id, TestimonialDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    TestimonialMapper.updateEntity(dto, entity);
                    return TestimonialMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}