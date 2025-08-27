package com.citykart.admin.controller;

import com.citykart.admin.dto.AdminLoginDTO;
import com.citykart.admin.dto.AdminLoginResponse;
import com.citykart.admin.dto.AdminRegisterDTO;
import com.citykart.admin.entity.Admin;
import com.citykart.admin.service.IFace.AdminAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AdminRegisterDTO dto) {
        Admin admin = adminAuthService.register(dto);
        return ResponseEntity.ok("Admin registered with email: " + admin.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> login(@RequestBody AdminLoginDTO dto) {
        String token = adminAuthService.login(dto);
        return ResponseEntity.ok(new AdminLoginResponse(token));
    }
}

