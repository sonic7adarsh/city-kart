package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ContactInfoDTO extends BaseDTO {
    private String label;
    private String value;
    private String icon;
}

