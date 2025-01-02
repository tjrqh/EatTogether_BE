package com.project.eatTogether.application.dto.differed.map;

import com.project.eatTogether.domain.entity.differed.Coordinates;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MapResponseDto {
    Long RsCoordinatesId;
//    float R
    String RestaurantAddr;

    public static MapResponseDto entityToDto(Coordinates coordinates) {
        return MapResponseDto.builder()
                .RsCoordinatesId(coordinates.getRsCoordinatesId())
                .RestaurantAddr(coordinates.getRestaurantAddr())
                .build();
    }
}
