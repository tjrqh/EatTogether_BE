package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.domain.RsLocationCategories;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.RsLocationHotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RsCuisineCategoriesService {

    @Autowired
    private RsCuisineCategoriesRepository cuisineCategoriesRepository;

    @Autowired
    private RsLocationHotRepository rsLocationHotRepository;

    public RsCuisineCategoriesDTO getCuisineCategoryByName(String categoryName) {
        RsCuisineCategories entity = cuisineCategoriesRepository.findByRsCuisineCategoryName(categoryName);
        RsLocationCategories categories = rsLocationHotRepository.findByRsLocationName(entity.getRsRestaurant().getRsCuisineCategories().getRsCuisineCategoryName());
        if (entity != null) {
            RsCuisineCategoriesDTO rsCuisineCategoriesDTO = RsCuisineCategoriesDTO.builder()
                    .rsLocationName(categories.getRsLocationName())
                    .rsCuisineCategoryName(entity.getRsCuisineCategoryName())
                    .rsId(entity.getRsRestaurant().getRsId())
                    .rsName(entity.getRsRestaurant().getRsName())
                    .rsAvgRate(entity.getRsRestaurant().getRsAvgRate())
                    .build();
            return rsCuisineCategoriesDTO;
        }
        // 엔티티가 없을 경우 처리 (필요한 경우)
        return null;
    }
}
