package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsLocationCategoriesDTO {
    private Long rsLocationId; // 위치카테고리 ID
    private Long rsId; // 식당 ID
    private String rsLocationName; // 위치 이름
}
