package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsRestaurantDTO {
    private Long rsId; // 식당 ID
    private String rsName; // 식당 이름
    private String rsPhone; // 식당 연락처
    private String rsPark; // 식당 주차
    private String rsTime; // 식당 영업 시간
    private String rsState; // 식당 상태 (오픈, 마감)
    private String rsReviewCount; // 식당 리뷰 수
    private int rsBookmarkCount; // 식당 북마크 수
    private int rsAvgRate; // 식당 평균 평점
    private int rsReservationCount; // 식당 예약 횟수
}
