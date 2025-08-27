package com.citykart.service;

import com.citykart.dtos.BookItemDTO;
import com.citykart.entities.BookItem;
import com.citykart.mappers.BookItemMapper;
import com.citykart.repos.BookItemRepository;
import com.citykart.service.IFace.BookItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookItemServiceImpl implements BookItemService {

    private final BookItemRepository repository;

    @Override
    public List<BookItemDTO> getAll() {
        return repository.findAll().stream()
                .map(BookItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookItemDTO getById(Long id) {
        return repository.findById(id)
                .map(BookItemMapper::toDto)
                .orElse(null);
    }

    @Override
    public BookItemDTO create(BookItemDTO dto) {
        BookItem entity = BookItemMapper.toEntity(dto);
        return BookItemMapper.toDto(repository.save(entity));
    }

    @Override
    public BookItemDTO update(Long id, BookItemDTO dto) {
        return repository.findById(id)
                .map(entity -> {
                    BookItemMapper.updateEntity(dto, entity);
                    return BookItemMapper.toDto(repository.save(entity));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}