package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsLocationHotDTO;
import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.domain.RsLocationCategories;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.RsLocationHotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RsLocationHotService {

    @Autowired
    private RsLocationHotRepository locationHotRepository;

    @Autowired
    private RsCuisineCategoriesRepository rsCuisineCategoriesRepository;

    public RsLocationHotDTO getLocationHotByName(String rsLocationCategoriesName) {
        RsLocationCategories entity = locationHotRepository.findByRsLocationName(rsLocationCategoriesName);
        RsCuisineCategories categories = rsCuisineCategoriesRepository.findByRsCuisineCategoryName(entity.getRsRestaurant().getRsCuisineCategories().getRsCuisineCategoryName());
        if (entity != null) {
            RsLocationHotDTO rsLocationHotDTO = RsLocationHotDTO.builder()
                    .rsLocationName(entity.getRsLocationName())
                    .rsCuisineCategoryName(categories.getRsCuisineCategoryName())
                    .rsId(entity.getRsRestaurant().getRsId())
                    .rsName(entity.getRsRestaurant().getRsName())
                    .rsAvgRate(entity.getRsRestaurant().getRsAvgRate())
                    .build();
            return rsLocationHotDTO;
        }
        // 엔티티가 없을 경우 처리 (필요한 경우)
        return null;
    }
}
