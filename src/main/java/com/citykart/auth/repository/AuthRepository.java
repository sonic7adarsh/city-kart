package com.citykart.auth.repository;

import com.citykart.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    Optional<Auth> findTopByPhoneOrderByCreatedAtDesc(String phone);
}

