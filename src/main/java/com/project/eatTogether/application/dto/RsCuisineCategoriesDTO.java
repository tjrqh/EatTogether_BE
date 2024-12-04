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
    private Long rsCuisineId; // 식당종류카테고리id
    private Long rsId; // 식당 id
    private String rsCuisineCategoryName; // 식당종류이름
    private String rsName; // 식당 이름
    private Byte rsAvgRate; // 식당 평균 평점
}

