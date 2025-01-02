package com.project.eatTogether.application.dto.differed.restaurant;

import com.project.eatTogether.domain.entity.differed.CuisineCategories;
import com.project.eatTogether.domain.entity.differed.LocationCategories;
import com.project.eatTogether.domain.entity.differed.Restaurant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private CuisineCategories cuisineCategories;
    private LocationCategories locationCategories;
    private String openingHours;
    private Double avgRate;

    public RestaurantResponseDto(Long id, String name,
                                 CuisineCategories cuisineCategories,
                                 LocationCategories locationCategories,
                                 String openingHours, Double avgRate) {
        this.id = id;
        this.name = name;
        this.cuisineCategories = cuisineCategories;
        this.locationCategories = locationCategories;
        this.openingHours = openingHours;
        this.avgRate = avgRate;
    }

    public static RestaurantResponseDto from(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .cuisineCategories(restaurant.getCuisineCategories())
                .locationCategories(restaurant.getLocationCategories())
                .openingHours(restaurant.getOpeningHours())
                .avgRate(restaurant.getAvgRate())
                .build();
    }

}
