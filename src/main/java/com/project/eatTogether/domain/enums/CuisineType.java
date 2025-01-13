package com.project.eatTogether.domain.enums;

public enum CuisineType {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    FUSION("퓨전"),
    CAFE("카페"),
    OTHER("기타"),
    ITALIAN("이탈리안"),
    FRENCH("프랑스식"),
    INDIAN("인도식"),
    MEXICAN("멕시칸"),
    AMERICAN("미국식"),
    VIETNAMESE("베트남식"),
    THAI("태국식");

    private final String displayName;

    CuisineType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}