package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.domain.entity.RsCuisineCategories;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RsCuisineCategoriesService {

    private static final Logger logger = LoggerFactory.getLogger(RsCuisineCategoriesService.class);

    @Autowired
    private RsCuisineCategoriesRepository cuisineCategoriesRepository;

    public List<RsCuisineCategoriesDTO> getCuisineCategoryByName(String categoryName, int page, int size) {
        // categoryName이 null이거나 빈 문자열일 경우 빈 리스트 반환
        if (categoryName == null || categoryName.trim().isEmpty()) {
            logger.warn("Empty or null category name provided.");
            return List.of();
        }

        Pageable pageable = PageRequest.of(page, size);

        // 레포지토리 호출 (FindByRsCuisineCategoryName 메서드)
        Page<RsCuisineCategories> entitiesPage = cuisineCategoriesRepository.findByRsCuisineCategoryName(categoryName, pageable);

        // 결과 DTO로 변환
        return entitiesPage
                .stream()
                .map(cuisine -> {
                    RsRestaurant restaurant = cuisine.getRsRestaurant();
                    RsCoordinates coordinates = restaurant.getRsCoordinates();  // RsCoordinates 객체 접근

                    // 로그 제거 대신, 필요한 경우 로깅 처리
                    logger.debug("Restaurant Info: {}", restaurant);
                    logger.debug("Coordinates Info: {}", coordinates);

                    return RsCuisineCategoriesDTO.builder()
                            .restaurantAddr(coordinates != null ? coordinates.getRestaurantAddr() : "주소 없음")
                            .rsCuisineCategoryName(cuisine.getRsCuisineCategoryName())
                            .rsId(restaurant.getRsId())
                            .rsName(restaurant.getRsName())
                            .rsAvgRate(restaurant.getRsAvgRate())  // 수정된 부분
                            .rsInfo(restaurant.getRsInfo())
                            .restaurantLat(coordinates != null ? coordinates.getRestaurantLat() : 0.0f)
                            .restaurantLong(coordinates != null ? coordinates.getRestaurantLong() : 0.0f)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
