package com.project.eatTogether.application.dto.restaurantDto;

import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.domain.entity.RsRestaurant;
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

    public static RestaurantLocationDto fromEntity(RsRestaurant restaurant) {
        RsCoordinates coordinates = restaurant.getRsCoordinates();
        return new RestaurantLocationDto(
                restaurant.getRsId(),
                restaurant.getRsName(),
                coordinates.getRestaurantLat(),
                coordinates.getRestaurantLong(),
                coordinates.getRestaurantAddr(),
                restaurant.getRsPhone(),
                restaurant.getRsAvgRate()
        );
    }
}
