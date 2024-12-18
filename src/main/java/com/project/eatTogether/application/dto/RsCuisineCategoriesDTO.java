package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsCuisineCategoriesDTO {
    private String restaurantAddr; // 위치 이름 (기존 rsLocationName 대체)
    private String rsCuisineCategoryName; // 식당 종류 이름
    private Long rsId; // 식당 ID
    private String rsName; // 식당 이름
    private int rsAvgRate; // 식당 평균 평점
    private String rsInfo; // 식당 정보
    private float restaurantLat; // 식당 위도
    private float restaurantLong; // 식당 경도
}
