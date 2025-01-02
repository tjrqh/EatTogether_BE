package com.project.eatTogether.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN", "관리자"),
    OWNER("ROLE_OWNER", "식당주인"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final String title;

    @Override
    public String toString() {
        return this.key;  // toString()이 key 값을 반환하도록 수정
    }
}
