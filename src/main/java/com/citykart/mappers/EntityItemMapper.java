package com.citykart.mappers;

import com.citykart.dtos.*;
import com.citykart.entities.*;
import java.util.List;
import static com.citykart.mappers.MapperUtils.*;

/**
 * Manual, dependency-free mapper for EntityItem <-> EntityItemDTO.
 * Handles nested collections and keeps JPA back-references consistent.
 */
public final class EntityItemMapper {
    private EntityItemMapper() {}

    // -------- Entity -> DTO --------
    public static EntityItemDTO toDto(EntityItem e) {
        if (e == null) return null;
        List<EntityProductDTO> productDtos = mapList(e.getProducts(), EntityProductMapper::toDto);
        List<SectionDTO> sectionDtos = mapList(e.getSections(), SectionMapper::toDto);
        List<TestimonialDTO> testimonialDtos = mapList(e.getTestimonials(), TestimonialMapper::toDto);
        List<BookItemDTO> bookingDtos = mapList(e.getBookings(), BookItemMapper::toDto);
        List<ContactInfoDTO> contactDtos = mapList(e.getContacts(), ContactInfoMapper::toDto);
        List<SocialLinkDTO> socialDtos = mapList(e.getSocialLinks(), SocialLinkMapper::toDto);
        List<AmenityDTO> amenityDtos = mapList(e.getAmenities(), AmenityMapper::toDto);

        return EntityItemDTO.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .name(e.getName())
                .slug(e.getSlug())
                .type(e.getType())
                .products(productDtos)
                .sections(sectionDtos)
                .testimonials(testimonialDtos)
                .bookings(bookingDtos)
                .contacts(contactDtos)
                .socialLinks(socialDtos)
                .amenities(amenityDtos)
                .build();
    }

    // -------- DTO -> Entity (detached) --------
    public static EntityItem toEntity(EntityItemDTO d) {
        if (d == null) return null;
        EntityItem e = new EntityItem();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setSlug(d.getSlug());
        e.setType(d.getType());

        // child collections
        List<EntityProduct> products = mapList(d.getProducts(), EntityProductMapper::toEntity);
        List<Section> sections = mapList(d.getSections(), SectionMapper::toEntity);
        List<Testimonial> testimonials = mapList(d.getTestimonials(), TestimonialMapper::toEntity);
        List<BookItem> bookings = mapList(d.getBookings(), BookItemMapper::toEntity);
        List<ContactInfo> contacts = mapList(d.getContacts(), ContactInfoMapper::toEntity);
        List<SocialLink> socialLinks = mapList(d.getSocialLinks(), SocialLinkMapper::toEntity);
        List<Amenity> amenities = mapList(d.getAmenities(), AmenityMapper::toEntity);

        e.setProducts(products);
        e.setSections(sections);
        e.setTestimonials(testimonials);
        e.setBookings(bookings);
        e.setContacts(contacts);
        e.setSocialLinks(socialLinks);
        e.setAmenities(amenities);

        // back-refs
        for (EntityProduct p : products) EntityProductMapper.attachParent(p, e);
        for (Section s : sections) SectionMapper.attachParent(s, e);
        for (Testimonial t : testimonials) TestimonialMapper.attachParent(t, e);
        for (BookItem b : bookings) { b.setEntity(e); }
        for (ContactInfo c : contacts) ContactInfoMapper.attachParent(c, e);
        for (SocialLink s : socialLinks) SocialLinkMapper.attachParent(s, e);
        for (Amenity a : amenities) AmenityMapper.attachParent(a, e);

        return e;
    }

    // -------- Merge DTO into existing managed Entity --------
    public static void updateEntity(EntityItemDTO d, EntityItem e) {
        if (d == null || e == null) return;
        if (d.getName() != null) e.setName(d.getName());
        if (d.getSlug() != null) e.setSlug(d.getSlug());
        if (d.getType() != null) e.setType(d.getType());

        // For collections, replace with new copies and fix back-refs
        if (d.getProducts() != null) {
            List<EntityProduct> products = mapList(d.getProducts(), EntityProductMapper::toEntity);
            e.getProducts().clear();
            e.getProducts().addAll(products);
            for (EntityProduct p : products) EntityProductMapper.attachParent(p, e);
        }
        if (d.getSections() != null) {
            List<Section> sections = mapList(d.getSections(), SectionMapper::toEntity);
            e.getSections().clear();
            e.getSections().addAll(sections);
            for (Section s : sections) SectionMapper.attachParent(s, e);
        }
        if (d.getTestimonials() != null) {
            List<Testimonial> testimonials = mapList(d.getTestimonials(), TestimonialMapper::toEntity);
            e.getTestimonials().clear();
            e.getTestimonials().addAll(testimonials);
            for (Testimonial t : testimonials) TestimonialMapper.attachParent(t, e);
        }
        if (d.getBookings() != null) {
            List<BookItem> bookings = mapList(d.getBookings(), BookItemMapper::toEntity);
            e.getBookings().clear();
            e.getBookings().addAll(bookings);
            for (BookItem b : bookings) b.setEntity(e);
        }
        if (d.getContacts() != null) {
            List<ContactInfo> contacts = mapList(d.getContacts(), ContactInfoMapper::toEntity);
            e.getContacts().clear();
            e.getContacts().addAll(contacts);
            for (ContactInfo c : contacts) ContactInfoMapper.attachParent(c, e);
        }
        if (d.getSocialLinks() != null) {
            List<SocialLink> socialLinks = mapList(d.getSocialLinks(), SocialLinkMapper::toEntity);
            e.getSocialLinks().clear();
            e.getSocialLinks().addAll(socialLinks);
            for (SocialLink s : socialLinks) SocialLinkMapper.attachParent(s, e);
        }
        if (d.getAmenities() != null) {
            List<Amenity> amenities = mapList(d.getAmenities(), AmenityMapper::toEntity);
            e.getAmenities().clear();
            e.getAmenities().addAll(amenities);
            for (Amenity a : amenities) AmenityMapper.attachParent(a, e);
        }
    }

}
