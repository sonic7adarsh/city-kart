package com.citykart.service.IFace;

import com.citykart.dtos.TestimonialDTO;
import java.util.List;

public interface TestimonialService {
    List<TestimonialDTO> getAll();
    TestimonialDTO getById(Long id);
    TestimonialDTO create(TestimonialDTO dto);
    TestimonialDTO update(Long id, TestimonialDTO dto);
    void delete(Long id);
}