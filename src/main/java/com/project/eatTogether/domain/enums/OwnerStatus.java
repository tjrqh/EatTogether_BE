package com.project.eatTogether.domain.enums;

import lombok.Getter;

@Getter
public enum OwnerStatus {
    PENDING("승인 대기", "점주 승인을 대기중입니다."),
    APPROVED("승인 완료", "점주 승인이 완료되었습니다."),
    HOLD("보류", "점주 승인이 보류중입니다."),
    REJECTED("승인 거절", "점주 승인이 거절되었습니다."),
    SUSPENDED("자격 정지", "점주 자격이 정지되었습니다."),
    WITHDRAWN("탈퇴", "점주 자격이 취소되었습니다.");

    private final String status;
    private final String message;

    OwnerStatus(String status, String message) {
        this.status = status;
        this.message = message;
    }
}