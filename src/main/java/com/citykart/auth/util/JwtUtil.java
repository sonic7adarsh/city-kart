package com.citykart.auth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    public String generateToken(String phone) {
        return Jwts.builder()
                .setSubject(phone)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith( Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String extractPhone(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

//    public static void main(String argsp[]) throws Exception {
//        try {
//            String secret = "ThisIsASecretKeyWith32Chars!!@#@#@@@@##";
////            String token = Jwts.builder()
////                    .setSubject("7355460178")
////                    .setIssuedAt(new Date())
////                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
////                    .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
////                    .compact();
////            System.out.print(token);
//            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build()
//            .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI3MzU1NDYwMTc4IiwiaWF0IjoxNzUyMjY0MjQxLCJleHAiOjE3NTIzNTA2NDF9.M2sl5N8h7ZLyJ3OVyXFTWFVJ9MnVEDEcd6f4VRwLoVY");
//            System.out.println("true");
//        } catch (JwtException e) {
//            log.error("Something went wrong", e);
//            System.out.println("false");
//        }
//    }
}
