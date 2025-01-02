package com.project.eatTogether.infrastructure.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class JWTUtil {

    private static final Map<String, String> refreshTokenStore = new ConcurrentHashMap<>();
    private static final Map<String, Date> blacklistedTokens = new ConcurrentHashMap<>();

    private static String key = "9945e61ea9a3e23a0cb332358867ead438bcf0bc49c8b8e46a4bc325768e72ce09b7b18eece664980d268f4f46a1f5bb7e8299117d3fca93c2ac6b391bd81f2484d9ec92d61b80487af65ef225ad3d1e43f0e3f86b05347bbf8eeff8a67e0d41f5dd665897d7ef6b29a1f70599aa47c7992c53a12e0edbce951a81f15748a534e887388e9a79274bc0743ec77df29acc32337240b1a17b32d4387d3711f59d512599ea45de42ead06b8e99bca7431d28e34f0ac43c3664709d43cbba0f2f80c2c9a5cee93c033509826f2f2443519af11e0f897b994834993265aafc37189a7d592611b41230ad9455f5c61dc6004ce703a6ddc70d9d376828553b75f93b3fee";

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(key);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // Access Token 발급 (기본 만료 시간: 15분)
    public String generateToken(String email) {
        return generateToken(email, 1000 * 60 * 15);
    }

    // Refresh Token 발급 (기본 만료 시간: 7일)
    public String generateRefreshToken(String email) {
        String refreshToken = generateToken(email, 1000 * 60 * 60 * 24 * 7);
        saveRefreshToken(email, refreshToken);
        return refreshToken;
    }

    // JWT Token 생성
    private String generateToken(String email, long expiration) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            if (isTokenBlacklisted(token)) {
                log.debug("Token is blacklisted: {}", token);
                return false;
            }
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.debug("Error validating JWT Token: {}", token, e);
            return false;
        }
    }

    // BlackList 추가 (logout 시 호출)
    public void invalidateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
            blacklistedTokens.put(token, claims.getExpiration());
            cleanupExpiredBlacklistedTokens();
            log.debug("Token invalidated and blacklisted: {}", token);
        } catch (Exception e) {
            log.error("Error invalidating token: {}", token, e);
        }
    }

    // Cleanup expired blacklisted tokens
    private void cleanupExpiredBlacklistedTokens() {
        Date now = new Date();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().before(now));
    }

    // 토큰에서 이메일(사용자 정보) 추출
    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            log.error("Error extracting username from token: {}", token, e);
            return null;
        }
    }

    // 토큰이 BlackList에 있는지 확인
    private boolean isTokenBlacklisted(String token) {
        cleanupExpiredBlacklistedTokens();
        boolean isBlacklisted = blacklistedTokens.containsKey(token);
        log.debug("Token blacklist check: {}, Result: {}", token, isBlacklisted);
        return isBlacklisted;
    }

    // Refresh Token 저장
    private void saveRefreshToken(String email, String refreshToken) {
        try {
            refreshTokenStore.put(email, refreshToken);
            log.debug("Refresh token saved for email: {}", email);
        } catch (Exception e) {
            log.error("Error saving refresh token for email: {}", email, e);
        }
    }

    // Refresh Token 유효성 확인
    public boolean validateRefreshToken(String refreshToken) {
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(refreshToken).getBody();
            String email = claims.getSubject();
            String storedRefreshToken = refreshTokenStore.get(email);

            log.debug("Stored Refresh Token: {}", storedRefreshToken);
            log.debug("Refresh Token from Request: {}", refreshToken);

            boolean isValid = refreshToken.equals(storedRefreshToken);
            log.debug("Refresh Token Validation Result: {}", isValid);
            return isValid;
        } catch (MalformedJwtException malformedJwtException) {
            throw new CustomJWTException("Malformed");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new CustomJWTException("Expired");
        } catch (InvalidClaimException invalidClaimException) {
            throw new CustomJWTException("JWTError");
        } catch (Exception e) {
            throw new CustomJWTException("Error");
        }
    }

    // Refresh Token 재생성 및 저장 (기존 토큰 무효화)
    public String regenerateRefreshToken(String email) {
        try {
            refreshTokenStore.remove(email); // 기존 Refresh Token 삭제
            log.debug("Old refresh token deleted for email: {}", email);
            String newRefreshToken = generateRefreshToken(email);
            log.debug("New Refresh Token Generated: {}", newRefreshToken);
            return newRefreshToken;
        } catch (Exception e) {
            log.error("Error regenerating refresh token for email: {}", email, e);
            return null;
        }
    }

    // 새로운 Access Token 발급 (Refresh Token 사용)
    public String refreshAccessToken(String refreshToken) {
        if (validateRefreshToken(refreshToken)) {
            String email = extractUsername(refreshToken);
            return generateToken(email); // 새로운 Access Token 발급
        } else {
            throw new IllegalArgumentException("Invalid refresh token");
        }
    }
}