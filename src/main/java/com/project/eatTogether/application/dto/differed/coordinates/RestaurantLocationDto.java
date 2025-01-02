package com.project.eatTogether.application.dto.differed.coordinates;

import com.project.eatTogether.domain.entity.differed.Coordinates;
import com.project.eatTogether.domain.entity.differed.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantLocationDto {
    private Long id;
    private String name;
    private float latitude;
    private float longitude;
    private String address;
    private String phone;
    private Double avgRate;

    public static RestaurantLocationDto fromEntity(Restaurant restaurant) {
        Coordinates coordinates = restaurant.getCoordinates();
        return new RestaurantLocationDto(
                restaurant.getId(),
                restaurant.getName(),
                coordinates.getRestaurantLat(),
                coordinates.getRestaurantLong(),
                coordinates.getRestaurantAddr(),
                restaurant.getPhone(),
                restaurant.getAvgRate()
        );
    }
}
