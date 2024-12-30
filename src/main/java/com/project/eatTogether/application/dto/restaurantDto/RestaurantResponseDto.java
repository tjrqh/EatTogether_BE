package com.project.eatTogether.application.dto.restaurantDto;

import com.project.eatTogether.domain.entity.RsCuisineCategories;
import com.project.eatTogether.domain.entity.RsLocationCategories;
import com.project.eatTogether.domain.entity.RsRestaurant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private RsCuisineCategories cuisineCategories;
    private RsLocationCategories locationCategories;
    private String openingHours;
    private Double avgRate;

    public RestaurantResponseDto(Long id, String name,
                                 RsCuisineCategories cuisineCategories,
                                 RsLocationCategories locationCategories,
                                 String openingHours, Double avgRate) {
        this.id = id;
        this.name = name;
        this.cuisineCategories = cuisineCategories;
        this.locationCategories = locationCategories;
        this.openingHours = openingHours;
        this.avgRate = avgRate;
    }

    public static RestaurantResponseDto from(RsRestaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getRsId())
                .name(restaurant.getRsName())
                .cuisineCategories(restaurant.getRsCuisineCategories())
                .locationCategories(restaurant.getRsLocationCategories())
                .openingHours(restaurant.getRsTime())
                .avgRate(restaurant.getRsAvgRate())
                .build();
    }

}
