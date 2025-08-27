package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SectionContentDTO extends BaseDTO {
    private String title;
    private String subtitle;
    private String description;
    private String icon;
    private String imageUrl;
}

