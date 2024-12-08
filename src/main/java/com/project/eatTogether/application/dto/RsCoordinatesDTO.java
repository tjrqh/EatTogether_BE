package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsCoordinatesDTO {
    private Long rsCoordinatesId; // 좌표 ID
    private float restaurantLat; // 식당 위도
    private float restaurantLong; // 식당 경도
    private String restaurantAddr; // 식당 도로명 주소
}
