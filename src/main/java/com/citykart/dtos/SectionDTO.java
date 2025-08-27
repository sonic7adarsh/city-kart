package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SectionDTO extends BaseDTO {
    private String name;   // hero, about, features, gallery
    private boolean visible;
    private List<SectionContentDTO> contents;
}
