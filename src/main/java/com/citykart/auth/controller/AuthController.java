package com.citykart.auth.controller;

import com.citykart.auth.dto.OtpRequestDTO;
import com.citykart.auth.dto.OtpVerifyDTO;
import com.citykart.auth.service.IFace.AuthService;
import com.citykart.auth.util.JwtUtil;
import com.citykart.user.dto.UserDTO;
import com.citykart.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, UserService userService, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO dto) {
        userService.registerIfNotExists(dto);
        return ResponseEntity.ok("Registered user with role: " + dto.getRole());
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequestDTO dto) {
        String otp = authService.generateAndSendOtp(dto);
        return ResponseEntity.ok("OTP Sent: " + otp); // test only
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody OtpVerifyDTO dto) {
            return ResponseEntity.ok(authService.verifyAndGenerateToken(dto));
    }
}
