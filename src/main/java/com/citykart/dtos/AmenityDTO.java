package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AmenityDTO extends BaseDTO {
    private String name;
    private String icon;
}
