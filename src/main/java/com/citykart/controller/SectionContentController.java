package com.citykart.controller;

import com.citykart.dtos.SectionContentDTO;
import com.citykart.service.IFace.SectionContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/section-contents")
@RequiredArgsConstructor
public class SectionContentController {

    private final SectionContentService service;

    @GetMapping
    public ResponseEntity<List<SectionContentDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionContentDTO> getById(@PathVariable Long id) {
        SectionContentDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SectionContentDTO> create(@RequestBody SectionContentDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionContentDTO> update(@PathVariable Long id, @RequestBody SectionContentDTO dto) {
        SectionContentDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}