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
    private Long rsId; // 식당 ID
    private Long rsAmenityId; // 편의시설 코드 ID
}
