package com.project.eatTogether.application.dto.differed.login;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String nickname;
    private String name;
    private String phone;
    private String role;
    private Long id;
}