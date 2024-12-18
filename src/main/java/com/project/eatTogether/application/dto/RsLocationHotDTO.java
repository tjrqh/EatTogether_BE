package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RsLocationHotDTO {
    private String rsLocationName; // 위치 이름
    private String rsCuisineCategoryName; // 식당종류 이름
    private Long rsId; // 식당 ID
    private String rsName; // 식당 이름
    private float rsAvgRate; // 식당 평균 평점
}
