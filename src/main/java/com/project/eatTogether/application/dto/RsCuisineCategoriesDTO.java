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
    private Long rsCuisineCategoryId; // 식당종류카테고리 ID
    private String rsCuisineCategoryName; // 식당종류 이름
    private Long rsId; // 식당 ID
    private String rsName; // 식당 이름
    private int rsAvgRate; // 식당 평균 평점
}
