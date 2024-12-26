package com.project.eatTogether.application.dto;

import jakarta.persistence.Column;
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
    private int rsBookmarkCount; // 즐겨찾기 개수 (추가)
    private int rsReviewCount; // 리뷰 개수 (추가)

    @Builder.Default
    private boolean rsQueueEnabled = true;  // 기본값 true, 줄서기 가능

    @Builder.Default
    private boolean isPrepaid = false;
}
