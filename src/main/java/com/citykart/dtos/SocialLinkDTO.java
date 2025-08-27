package com.citykart.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SocialLinkDTO extends BaseDTO {
    private String platform;
    private String url;
    private String icon;
}

