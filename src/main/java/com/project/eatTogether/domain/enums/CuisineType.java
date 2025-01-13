package com.project.eatTogether.domain.enums;

public enum CuisineType {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    FUSION("퓨전"),
    CAFE("카페"),
    OTHER("기타");

    private final String displayName;

    CuisineType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // 한글 이름으로 Enum 값을 찾는 메서드 추가
    public static CuisineType fromDisplayName(String displayName) {
        for (CuisineType cuisineType : values()) {
            if (cuisineType.getDisplayName().equalsIgnoreCase(displayName)) {
                return cuisineType;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 카테고리: " + displayName);
    }
}

