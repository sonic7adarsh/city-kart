package com.citykart.controller;

import com.citykart.dtos.EntityProductDTO;
import com.citykart.service.IFace.EntityProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entity-products")
@RequiredArgsConstructor
public class EntityProductController {

    private final EntityProductService service;

    @GetMapping
    public ResponseEntity<List<EntityProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityProductDTO> getById(@PathVariable Long id) {
        EntityProductDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EntityProductDTO> create(@RequestBody EntityProductDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityProductDTO> update(@PathVariable Long id, @RequestBody EntityProductDTO dto) {
        EntityProductDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}