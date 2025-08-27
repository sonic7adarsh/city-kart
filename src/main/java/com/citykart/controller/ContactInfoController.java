package com.citykart.controller;

import com.citykart.dtos.ContactInfoDTO;
import com.citykart.service.IFace.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-info")
@RequiredArgsConstructor
public class ContactInfoController {

    private final ContactInfoService service;

    @GetMapping
    public ResponseEntity<List<ContactInfoDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInfoDTO> getById(@PathVariable Long id) {
        ContactInfoDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ContactInfoDTO> create(@RequestBody ContactInfoDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInfoDTO> update(@PathVariable Long id, @RequestBody ContactInfoDTO dto) {
        ContactInfoDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}