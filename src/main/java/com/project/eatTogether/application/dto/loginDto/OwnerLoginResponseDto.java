package com.project.eatTogether.application.dto.loginDto;

import com.project.eatTogether.domain.enums.OwnerStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String userEmail;
    private String userName;
    private String userRole;
    private String rsDocumentBusinessId;
    private String rsName;
    private OwnerStatus ownerStatus;    // 점주 승인 상태
}