package com.project.eatTogether.application.dto.differed.login;

import com.project.eatTogether.domain.enums.OwnerStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String email;
    private String name;
    private String role;
    private String businessRegistrationNumber;
    private String restaurantName;
    private OwnerStatus ownerStatus;    // 점주 승인 상태
}