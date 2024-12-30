package com.project.eatTogether.application.dto.loginDto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String userEmail;
    private String userNickname;
    private String userName;
    private String userRole;
}