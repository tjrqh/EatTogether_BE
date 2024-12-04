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
    private int rsId; // 식당id
    private int restaurantLat; // 식당위도
    private int restaurantLong; // 식당경도
    private String restaurantAddr; // 식당도로명주소
}
