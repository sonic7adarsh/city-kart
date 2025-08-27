package com.citykart.mappers;

import com.citykart.dtos.BookItemDTO;
import com.citykart.entities.BookItem;
import com.citykart.entities.EntityItem;
import com.citykart.entities.EntityProduct;

/**
 * Maps booking DTOs. Does not resolve DB references; for DTO->Entity we create stubs
 * for EntityItem and EntityProduct when only IDs are available.
 */
public final class BookItemMapper {
    private BookItemMapper() {}

    public static BookItemDTO toDto(BookItem e) {
        if (e == null) return null;
        BookItemDTO d = BookItemDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .userId(e.getUserId())
                .checkIn(e.getCheckIn())
                .checkOut(e.getCheckOut())
                .guests(e.getGuests())
                .totalPrice(e.getTotalPrice())
                .status(e.getStatus())
                .guestName(e.getGuestName())
                .guestEmail(e.getGuestEmail())
                .guestPhone(e.getGuestPhone())
                .specialRequests(e.getSpecialRequests())
                .entityId(e.getEntity() != null ? e.getEntity().getId() : null)
                .productId(e.getProduct() != null ? e.getProduct().getId() : null)
                .build();
        return d;
    }

    public static BookItem toEntity(BookItemDTO d) {
        if (d == null) return null;
        BookItem e = new BookItem();
        e.setId(d.getId());
        e.setUserId(d.getUserId());
        e.setCheckIn(d.getCheckIn());
        e.setCheckOut(d.getCheckOut());
        e.setGuests(d.getGuests());
        e.setTotalPrice(d.getTotalPrice());
        e.setStatus(d.getStatus());
        e.setGuestName(d.getGuestName());
        e.setGuestEmail(d.getGuestEmail());
        e.setGuestPhone(d.getGuestPhone());
        e.setSpecialRequests(d.getSpecialRequests());
        if (d.getEntityId() != null) {
            EntityItem parent = new EntityItem();
            parent.setId(d.getEntityId());
            e.setEntity(parent);
        }
        if (d.getProductId() != null) {
            EntityProduct product = new EntityProduct();
            product.setId(d.getProductId());
            e.setProduct(product);
        }
        return e;
    }
    
    public static void updateEntity(BookItemDTO dto, BookItem entity) {
        if (dto == null || entity == null) return;
        
        if (dto.getUserId() != null) entity.setUserId(dto.getUserId());
        if (dto.getCheckIn() != null) entity.setCheckIn(dto.getCheckIn());
        if (dto.getCheckOut() != null) entity.setCheckOut(dto.getCheckOut());
        if (dto.getGuests() != null) entity.setGuests(dto.getGuests());
        if (dto.getTotalPrice() != null) entity.setTotalPrice(dto.getTotalPrice());
        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());
        if (dto.getGuestName() != null) entity.setGuestName(dto.getGuestName());
        if (dto.getGuestEmail() != null) entity.setGuestEmail(dto.getGuestEmail());
        if (dto.getGuestPhone() != null) entity.setGuestPhone(dto.getGuestPhone());
        if (dto.getSpecialRequests() != null) entity.setSpecialRequests(dto.getSpecialRequests());
        
        if (dto.getEntityId() != null && (entity.getEntity() == null || !dto.getEntityId().equals(entity.getEntity().getId()))) {
            EntityItem parent = new EntityItem();
            parent.setId(dto.getEntityId());
            entity.setEntity(parent);
        }
        
        if (dto.getProductId() != null && (entity.getProduct() == null || !dto.getProductId().equals(entity.getProduct().getId()))) {
            EntityProduct product = new EntityProduct();
            product.setId(dto.getProductId());
            entity.setProduct(product);
        }
        
        // BaseEntity fields
        if (dto.getUpdatedAt() != null) entity.setUpdatedAt(dto.getUpdatedAt());
    }
}
