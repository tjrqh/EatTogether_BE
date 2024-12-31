package com.project.eatTogether.infrastructure.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

    private static String key = "your-secret-key"; // 실제 프로젝트에서는 환경변수나 설정파일로 관리

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // Access Token 발급 (만료 시간: 15분)
    public String generateToken(String userEmail) {
        return generateToken(userEmail, 1000 * 60 * 15);
    }

    // Refresh Token 발급 (만료 시간: 7일)
    public String generateRefreshToken(String userEmail) {
        return generateToken(userEmail, 1000 * 60 * 60 * 24 * 7);
    }

    // JWT Token 생성
    private String generateToken(String userEmail, long expiration) {
        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    // 토큰에서 이메일 추출
    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            log.error("Error extracting username from token: {}", e.getMessage());
            return null;
        }
    }

    // Refresh Token으로 새로운 Access Token 발급
    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken)) {
            String userEmail = extractUsername(refreshToken);
            return generateToken(userEmail);
        } else {
            throw new CustomJWTException("Invalid refresh token");
        }
    }
}