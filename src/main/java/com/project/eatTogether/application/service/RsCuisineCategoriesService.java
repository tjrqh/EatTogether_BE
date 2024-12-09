/*
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.domain.RsLocationCategories;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RsCuisineCategoriesService {

    @Autowired
    private RsCuisineCategoriesRepository cuisineCategoriesRepository;

    public List<RsCuisineCategoriesDTO> getCuisineCategoryByName(String categoryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<RsCuisineCategories> entitiesPage = cuisineCategoriesRepository.findByRsCuisineCategoryName(categoryName, pageable);

        return entitiesPage
                .stream()
                .map(cuisine -> {
                    RsRestaurant restaurant = cuisine.getRsRestaurant();
                    RsLocationCategories locationCategory = restaurant.getRsLocationCategories();
                    return RsCuisineCategoriesDTO.builder()
                            .rsLocationName(locationCategory.getRsLocationName())
                            .rsCuisineCategoryName(cuisine.getRsCuisineCategoryName())
                            .rsId(restaurant.getRsId()).rsName(restaurant.getRsName())
                            .rsAvgRate(restaurant.getRsAvgRate())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
*/
