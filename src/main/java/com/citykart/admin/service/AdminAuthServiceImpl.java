package com.citykart.admin.service;

import com.citykart.admin.dto.AdminLoginDTO;
import com.citykart.admin.dto.AdminRegisterDTO;
import com.citykart.admin.entity.Admin;
import com.citykart.admin.repository.AdminRepository;
import com.citykart.admin.service.IFace.AdminAuthService;
import com.citykart.auth.util.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminRepository repo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthServiceImpl(AdminRepository repo, JwtUtil jwtUtil) {
        this.repo = repo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void initAdmin() {
        registerDefaultAdminIfNotExists();
    }
    public String login(final AdminLoginDTO dto) {
        Admin admin = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(admin.getEmail()); // 👈 Use email as subject
    }

    private void registerDefaultAdminIfNotExists() {
        if (repo.findByEmail("admin@citykart.com").isEmpty()) {
            repo.save(Admin.builder()
                    .email("admin@citykart.com")
                    .password(passwordEncoder.encode("admin123"))
                    .build());
        }
    }

    public Admin register(final AdminRegisterDTO dto) {
        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Admin already exists");
        }

        Admin admin = Admin.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        return repo.save(admin);
    }
}
