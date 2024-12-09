package com.project.eatTogether.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN", "관리자"),
    OWNER("ROLE_OWNER", "식당주인"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final String title;
}
