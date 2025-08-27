package com.citykart.auth.dto;

import com.citykart.user.enums.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequestDTO {
    private String phone;
    private Role role;
}
