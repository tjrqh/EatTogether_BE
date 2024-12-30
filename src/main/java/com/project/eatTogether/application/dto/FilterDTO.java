package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private Long rsId;            // 식당 ID
    private String rsName;        // 식당 이름
    private Double rsAvgRate;        // 평균 평점
    private int rsReviewCount;    // 리뷰 수
    private int rsBookmarkCount;  // 북마크 수
}
