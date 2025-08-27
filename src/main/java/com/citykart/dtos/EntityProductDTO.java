package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EntityProductDTO extends BaseDTO {
    private String name;
    private String type;       // ROOM, DISH, TICKET, ITEM
    private String category;
    private String description;
    private Double price;
    private Integer maxGuests;
    private boolean available;
    private List<String> images;
    private List<String> features;
}

