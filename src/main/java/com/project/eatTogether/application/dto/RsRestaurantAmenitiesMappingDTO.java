package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsRestaurantAmenitiesMappingDTO {
    private int rsId; // 식당 ID
    private String rsAmenityId; // 편의시설 코드 ID
}
