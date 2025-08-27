package com.citykart.controller;

import com.citykart.dtos.SocialLinkDTO;
import com.citykart.service.IFace.SocialLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social-links")
@RequiredArgsConstructor
public class SocialLinkController {

    private final SocialLinkService service;

    @GetMapping
    public ResponseEntity<List<SocialLinkDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialLinkDTO> getById(@PathVariable Long id) {
        SocialLinkDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SocialLinkDTO> create(@RequestBody SocialLinkDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialLinkDTO> update(@PathVariable Long id, @RequestBody SocialLinkDTO dto) {
        SocialLinkDTO updated = service.update(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}