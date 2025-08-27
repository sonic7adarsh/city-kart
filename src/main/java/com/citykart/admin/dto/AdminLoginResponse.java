package com.citykart.admin.dto;

import lombok.Data;

@Data
public class AdminLoginResponse {
    private String token;

    public AdminLoginResponse(String token) {
        this.token = token;
    }
}