package com.citykart.service.IFace;

import com.citykart.dtos.BookItemDTO;
import java.util.List;

public interface BookItemService {
    List<BookItemDTO> getAll();
    BookItemDTO getById(Long id);
    BookItemDTO create(BookItemDTO dto);
    BookItemDTO update(Long id, BookItemDTO dto);
    void delete(Long id);
}