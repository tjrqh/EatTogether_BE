package com.project.eatTogether.application.dto.differed.coordinates;

import com.project.eatTogether.domain.entity.differed.Coordinates;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsCoordinatesDto {
    private Long rsCoordinatesId;
    private float restaurantLat;
    private float restaurantLong;
    private String restaurantAddr;

    public static RsCoordinatesDto fromEntity(Coordinates coordinates) {
        return new RsCoordinatesDto(
                coordinates.getRsCoordinatesId(),
                coordinates.getRestaurantLat(),
                coordinates.getRestaurantLong(),
                coordinates.getRestaurantAddr()
        );
    }
}