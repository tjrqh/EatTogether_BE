package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsLocationHotDTO;
import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.domain.RsLocationCategories;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.infrastructure.RsLocationCategoriesRepository;
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
    private RsLocationCategoriesRepository rsLocationCategoriesRepository;

    public List<RsLocationHotDTO> getLocationHotByName(String rsLocationCategoriesName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<RsLocationCategories> categoriesPage = rsLocationCategoriesRepository.findByRsLocationName(rsLocationCategoriesName, pageable);

        return categoriesPage
                .stream()
                .map(locationCategory -> {
                    RsRestaurant restaurant = locationCategory.getRsRestaurant();
                    // 첫 번째 RsCuisineCategories를 가져옴 (get(0) 사용)
                    RsCuisineCategories cuisineCategory = restaurant.getRsCuisineCategories().get(0);  // 첫 번째 항목을 가져옵니다.

                    return RsLocationHotDTO.builder()
                            .rsLocationName(locationCategory.getRsLocationName())
                            .rsCuisineCategoryName(cuisineCategory.getRsCuisineCategoryName())
                            .rsId(restaurant.getRsId())
                            .rsName(restaurant.getRsName())
                            .rsAvgRate(restaurant.getRsAvgRate())  // 수정된 부분
                            .build();
                })
                .collect(Collectors.toList());
    }
}

