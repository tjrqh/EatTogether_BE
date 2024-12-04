/*
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RsCuisineCategoriesService {

    @Autowired
    private RsCuisineCategoriesRepository cuisineCategoriesRepository;

    public RsCuisineCategoriesDTO getCuisineCategoryByName(String categoryName) {
        RsCuisineCategories entity = cuisineCategoriesRepository.findByRsCuisineCategoryName(categoryName);
        if (entity != null) {
            RsCuisineCategoriesDTO rsCuisineCategoriesDTO = RsCuisineCategoriesDTO.builder()
                    .rsCuisineId(entity.getRs_cuisine_id())
                    .rsId(entity.getRestaurant().getRs_id())
                    .rsCuisineCategoryName(entity.getRs_cuisine_category_name())
                    .rsName(entity.getRestaurant().getRs_name())
                    .rsAvgRate(entity.getRestaurant().getRs_avg_rate())
                    .build();
            return rsCuisineCategoriesDTO;
        }
        // 엔티티가 없을 경우 처리 (필요한 경우)
        return null;
    }
}
*/
