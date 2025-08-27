package com.citykart.auth.service;


import com.citykart.auth.dto.OtpRequestDTO;
import com.citykart.auth.dto.OtpVerifyDTO;
import com.citykart.auth.entity.Auth;
import com.citykart.auth.repository.AuthRepository;
import com.citykart.auth.service.IFace.AuthService;
import com.citykart.auth.util.JwtUtil;
import com.citykart.notification.service.NotificationService;
import com.citykart.user.entity.User;
import com.citykart.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    public String verifyAndGenerateToken(OtpVerifyDTO dto) {
        Auth auth = authRepository.findTopByPhoneOrderByCreatedAtDesc(dto.getPhone())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (auth.isUsed()) throw new RuntimeException("OTP already used");

        if (!auth.getOtp().equals(dto.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        // Optional: check expiry (e.g., 5 mins)
        if (auth.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new RuntimeException("OTP expired");
        }

        auth.setUsed(true);
        authRepository.save(auth);

        return jwtUtil.generateToken(dto.getPhone());
    }


    public String generateAndSendOtp(OtpRequestDTO dto) {
        try {
            String otp = String.valueOf(new Random().nextInt(9000) + 1000);

            Auth authEntity = Auth.builder()
                    .phone(dto.getPhone())
                    .otp(otp)
                    .createdAt(LocalDateTime.now())
                    .used(false)
                    .build();

            authRepository.save(authEntity);
            User user = userService.getByPhone(dto.getPhone());
            notificationService.notifyOTP(user, otp, user.getName());

            return otp;
        } catch (Exception e) {
            log.error("Something went wrong while sending OTP", e);
        }
        return "failed";
        // In real apps, send via SMS
    }

}
