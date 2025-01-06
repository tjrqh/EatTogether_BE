package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.application.dto.RsLocationCategoriesDTO;
import com.project.eatTogether.application.dto.RsRestaurantDTO;
import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.domain.entity.RsCuisineCategories;
import com.project.eatTogether.domain.entity.RsLocationCategories;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.enums.CuisineType;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;
    private final RsCuisineCategoriesRepository rsCuisineCategoriesRepository;

    // 이름으로 검색
    public List<RsRestaurantDTO> searchByName(String query, int page, int size) {
        Pageable pageable = createPageable(page, size);
        return searchRepository.findByRsNameContainingIgnoreCase(query, pageable)
                .stream()
                .map(this::toRestaurantDTO)
                .collect(Collectors.toList());
    }

    // 카테고리로 검색
//    public List<RsCuisineCategoriesDTO> searchByCuisineCategory(String categoryName, int page, int size) {
//        Pageable pageable = createPageable(page, size);
//        return searchRepository.findByCuisineCategoryNameContainingIgnoreCase(categoryName, pageable)
//                .stream()
//                .map(this::toCuisineCategoryDTO)
//                .collect(Collectors.toList());
//    }

    public List<RsCuisineCategoriesDTO> searchByCuisineCategory(String categoryName, int page, int size) {
        try {
            // displayName으로 CuisineType 찾기
            CuisineType cuisineType = Arrays.stream(CuisineType.values())
                    .filter(type -> type.getDisplayName().equals(categoryName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid category name: " + categoryName));

            return searchByCuisineCategory(cuisineType, page, size);
        } catch (IllegalArgumentException e) {
            // 일치하는 카테고리가 없을 경우 빈 리스트 반환
            return Collections.emptyList();
        }
    }


    public List<RsCuisineCategoriesDTO> searchByCuisineCategory(CuisineType cuisineType, int page, int size) {
        Pageable pageable = createPageable(page, size);
        return searchRepository.findByRsCuisineCategories_Type(cuisineType, pageable)
                .stream()
                .map(RsCuisineCategoriesDTO::fromRestaurant)
                .collect(Collectors.toList());
    }

    public Page<RsRestaurant> searchByCuisineType(CuisineType cuisineType, Pageable pageable) {
        return searchRepository.findByRsCuisineCategories_Type(cuisineType, pageable);
    }

    // RsCuisineCategories를 직접 조회해야 하는 경우
    public List<RsCuisineCategoriesDTO> searchByCategoryType(CuisineType type, int page, int size) {
        Pageable pageable = createPageable(page, size);
        List<RsCuisineCategoriesDTO> result = new ArrayList<>();

        List<RsCuisineCategories> categories = rsCuisineCategoriesRepository.findByType(type, pageable).getContent();
        for (RsCuisineCategories category : categories) {
            for (RsRestaurant restaurant : category.getRsRestaurants()) {
                RsCuisineCategoriesDTO dto = RsCuisineCategoriesDTO.fromRestaurant(restaurant);
                if (dto != null) {
                    result.add(dto);
                }
            }
        }
        return result;
    }

    // CuisineType으로 직접 검색 (내부 로직에서 사용)
//    public List<RsCuisineCategoriesDTO> searchByCuisineCategory(CuisineType cuisineType, int page, int size) {
//        Pageable pageable = createPageable(page, size);
//        return searchRepository.findByType(cuisineType, pageable)
//                .stream()
//                .map(category -> RsCuisineCategoriesDTO.builder()
//                        .rsId(category.getRsRestaurant().getRsId())
//                        .rsName(category.getRsRestaurant().getRsName())
//                        .restaurantAddr(category.getRsRestaurant().getAddress().getFullAddress())
//                        .rsAvgRate(category.getRsRestaurant().getRsAvgRate())
//                        .rsInfo(category.getRsRestaurant().getRsInfo())
//                        .restaurantLat(category.getRsRestaurant().getAddress().getLatitude())
//                        .restaurantLong(category.getRsRestaurant().getAddress().getLatitude())
//                        .rsBookmarkCount(category.getRsRestaurant().getRsBookmarkCount())
//                        .rsReviewCount(category.getRsRestaurant().getRsReviewCount())
//                        .rsQueueEnabled(category.getRsRestaurant().isRsQueueEnabled())
//                        .isPrepaid(category.getRsRestaurant().isPrepaid())
//                        .cuisineType(cuisineType.getDisplayName())
//                        .build())
//                .collect(Collectors.toList());
//    }
    
    // 위치로 검색
    public List<RsLocationCategoriesDTO> searchByLocation(String locationName, int page, int size) {
        Pageable pageable = createPageable(page, size);
        return searchRepository.findByLocationNameContainingIgnoreCase(locationName, pageable)
                .stream()
                .map(this::toLocationCategoryDTO)
                .collect(Collectors.toList());
    }

    // 페이징 객체 생성
    private Pageable createPageable(int page, int size) {
        return PageRequest.of(page, size);
    }

    // DTO 변환 메서드들
    private RsRestaurantDTO toRestaurantDTO(RsRestaurant restaurant) {
        return new RsRestaurantDTO(
                restaurant.getRsId(),
                restaurant.getRsName(),
                restaurant.getRsPhone(),
                restaurant.getRsInfo(),
                restaurant.getRsTime(),
                restaurant.getRsState(),
                restaurant.getRsReviewCount(),
                restaurant.getRsBookmarkCount(),
                restaurant.getRsAvgRate(),
                restaurant.getRsReservationCount()
        );
    }

//    private RsCuisineCategoriesDTO toCuisineCategoryDTO(RsRestaurant restaurant) {
//        // RsCuisineCategories 객체 리스트를 가져옴
//        List<RsCuisineCategories> cuisineCategories = restaurant.getRsCuisineCategories();
//        RsLocationCategories locationCategory = restaurant.getRsLocationCategory();
//
//        // 첫 번째 RsCuisineCategories 객체를 가져옴, 리스트가 비어있을 경우 null 처리
//        RsCuisineCategories cuisineCategory = (cuisineCategories != null && !cuisineCategories.isEmpty())
//                ? cuisineCategories.get(0)
//                : null;
//
//        // RsCuisineCategories 객체가 null일 경우 기본값 처리
//        RsCuisineCategoriesDTO cuisineCategoryDTO = cuisineCategory != null
//                ? new RsCuisineCategoriesDTO(
//                locationCategory != null ? locationCategory.getRsLocationName() : "Unknown", // 위치 이름
//                cuisineCategory.getRsCuisineCategoryName(),
//                restaurant.getRsId(),
//                restaurant.getRsName(),
//                restaurant.getRsAvgRate(),
//                restaurant.getRsInfo(),
//                getLatitude(restaurant),  // 위도
//                getLongitude(restaurant), // 경도
//                restaurant.getRsBookmarkCount(), // 즐겨찾기 개수
//                restaurant.getRsReviewCount(),  // 리뷰 개수
//                restaurant.isRsQueueEnabled(), // 줄서기 가능 여부
//                restaurant.isPrepaid() // 선결제 여부 (추가된 필드)
//        )
//                : null;  // RsCuisineCategories가 null이면 null 반환
//
//        return cuisineCategoryDTO;
//    }

    // 위치 정보 가져오기 (위도)
    private float getLatitude(RsRestaurant restaurant) {
        RsCoordinates coordinates = restaurant.getRsCoordinates();
        return coordinates != null ? coordinates.getRestaurantLat() : 0.0f;
    }

    // 위치 정보 가져오기 (경도)
    private float getLongitude(RsRestaurant restaurant) {
        RsCoordinates coordinates = restaurant.getRsCoordinates();
        return coordinates != null ? coordinates.getRestaurantLong() : 0.0f;
    }

    private RsLocationCategoriesDTO toLocationCategoryDTO(RsRestaurant restaurant) {
        RsLocationCategories locationCategory = restaurant.getRsLocationCategories();

        return new RsLocationCategoriesDTO(
                locationCategory != null ? locationCategory.getRsLocationId() : null,
                restaurant.getRsId(),
                locationCategory != null ? locationCategory.getRsLocationName() : "Unknown"
        );
    }
}
