package com.project.eatTogether.application.dto.map;

import com.project.eatTogether.domain.entity.RsCoordinates;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MapResponseDto {
    Long RsCoordinatesId;
//    float R
    String RestaurantAddr;

    public static MapResponseDto entityToDto(RsCoordinates coordinates) {
        return MapResponseDto.builder()
                .RsCoordinatesId(coordinates.getRsCoordinatesId())
                .RestaurantAddr(coordinates.getRestaurantAddr())
                .build();
    }
}
