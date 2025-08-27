package com.citykart.controller;

import com.citykart.dtos.EntityItemDTO;
import com.citykart.service.IFace.EntityItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entities")
@RequiredArgsConstructor
public class EntityItemController {

    private final EntityItemService service;

    @GetMapping
    public ResponseEntity<List<EntityItemDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityItemDTO> getById(@PathVariable Long id) {
        EntityItemDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EntityItemDTO> create(@RequestBody EntityItemDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityItemDTO> update(@PathVariable Long id, @RequestBody EntityItemDTO dto) {
        EntityItemDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
