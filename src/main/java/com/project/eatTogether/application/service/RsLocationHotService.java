package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsLocationHotDTO;
import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.domain.RsLocationCategories;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.RsLocationHotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RsLocationHotService {

    @Autowired
    private RsCuisineCategoriesRepository rsCuisineCategoriesRepository;

    public List<RsLocationHotDTO> getLocationHotByName(String rsLocationCategoriesName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<RsCuisineCategories> categoriesPage = rsCuisineCategoriesRepository.findByRsCuisineCategoryName(rsLocationCategoriesName, pageable);

        return categoriesPage
                .stream()
                .map(cuisine -> {
                    RsRestaurant restaurant = cuisine.getRsRestaurant();
                    RsLocationCategories locationCategory = restaurant.getRsLocationCategories();
                    return RsLocationHotDTO.builder()
                            .rsLocationName(locationCategory.getRsLocationName())
                            .rsCuisineCategoryName(cuisine.getRsCuisineCategoryName())
                            .rsId(locationCategory.getRsRestaurant().getRsId())
                            .rsName(locationCategory.getRsRestaurant().getRsName())
                            .rsAvgRate(locationCategory.getRsRestaurant().getRsAvgRate())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
