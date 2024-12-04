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
    private int rsLocationId; // 위치카테고리id
    private int rsId; // 식당id
    private String rsLocationName; // 위치이름
}
