/*
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.*;
import com.project.eatTogether.domain.*;
import com.project.eatTogether.infrastructure.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RsRestaurantDetailService {

    private final RsRestaurantRepository restaurantRepository;
    private final RsMenuRepository menuRepository;
    private final RsCoordinatesRepository coordinatesRepository;
    private final RsNewsRepository newsRepository;
    private final RsRestaurantAmenitiesMappingRepository amenitiesMappingRepository;
    private final RsAmenitiesRepository amenitiesRepository;
    private final RsLocationCategoriesRepository locationCategoriesRepository;
    private final RsReviewsRepository reviewsRepository;

    public RsRestaurantDetailDTO getRestaurantDetails(Long rsId, int page, int size) {
        RsRestaurant restaurant = restaurantRepository.findById(rsId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        List<RsMenusDTO> menuItems = menuRepository.findByRsRestaurantRsId(rsId, PageRequest.of(page, size))
                .stream()
                .map(menu -> RsMenusDTO.builder()
                        .menuId(menu.getRsMenuId())
                        .rsId(menu.getRsRestaurant().getRsId())
                        .menuName(menu.getRsMenuName())
                        .menuDesc(menu.getRsMenuDesc())
                        .menuPrice(menu.getRsMenuPrice())
                        .menuState(menu.getRsMenuState())
                        .menuAppear(menu.getRsMenuAppear())
                        .menuPhotoOrigin(menu.getRsMenuPhotoOrigin())
                        .menuPhotoPath(menu.getRsMenuPhotoPath())
                        .menuPhotoName(menu.getRsMenuPhotoName())
                        .menuCreatedAt(menu.getRsMenuCreatedAt())
                        .menuUpdatedAt(menu.getRsMenuUpdatedAt())
                        .menuDeletedAt(menu.getRsMenuDeletedAt())
                        .build())
                .collect(Collectors.toList());

        RsCoordinates coordinates = coordinatesRepository.findByRsRestaurantRsId(rsId);
        RsCoordinatesDTO coordinatesDTO = RsCoordinatesDTO.builder()
                .rsCoordinatesId(coordinates.getRsCoordinatesId())
                .restaurantLat(coordinates.getRestaurantLat())
                .restaurantLong(coordinates.getRestaurantLong())
                .restaurantAddr(coordinates.getRestaurantAddr())
                .build();

        List<RsNewsDTO> newsItems = newsRepository.findByRsRestaurantRsId(rsId)
                .stream()
                .map(news -> RsNewsDTO.builder()
                        .rsNewsId(news.getRsNewsId())
                        .rsId(news.getRsRestaurant().getRsId())
                        .rsNewsContent(news.getRsNewsContent())
                        .rsNewsPublishedCreatedAt(news.getRsNewsPublishedCreatedAt())
                        .rsNewsUpdatedAt(news.getRsNewsUpdatedAt())
                        .rsNewsDeletedAt(news.getRsNewsDeletedAt())
                        .build())
                .collect(Collectors.toList());

        List<Long> amenitiesIds = amenitiesMappingRepository.findByRsRestaurantRsId(rsId)
                .stream()
                .map(mapping -> mapping.getRsAmenities().getRsAmenityId())
                .toList();

        List<RsAmenitiesDTO> amenities = amenitiesIds.stream()
                .map(id -> {
                    RsAmenities amenity = amenitiesRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Amenity not found"));
                    return RsAmenitiesDTO.builder()
                            .rsAmenityId(amenity.getRsAmenityId())
                            .rsAmenityName(amenity.getRsAmenityName())
                            .build();
                })
                .collect(Collectors.toList());

        RsLocationCategories locationCategory = locationCategoriesRepository.findByRsRestaurantRsId(rsId);
        RsLocationCategoriesDTO locationCategoryDTO = RsLocationCategoriesDTO.builder()
                .rsLocationId(locationCategory.getRsLocationId())
                .rsId(locationCategory.getRsRestaurant().getRsId())
                .rsLocationName(locationCategory.getRsLocationName())
                .build();

        List<RsReviewDTO> reviews = reviewsRepository.findByRsRestaurantRsId(rsId, PageRequest.of(page, size))
                .stream()
                .map(review -> RsReviewDTO.builder()
                        .reviewId(review.getRsReviewId())
                        .userId(review.getUser().getUserId())
                        .rsId(review.getRsRestaurant().getRsId())
                        .reviewContent(review.getRsReviewContent())
                        .reviewRate(review.getRsReviewRate())
                        .reviewCreatedAt(review.getRsReviewCreatedAt())
                        .reviewState(review.getRsReviewState())
                        .reviewLike(review.getRsReviewLike())
                        .build())
                .collect(Collectors.toList());

        RsRestaurantDTO restaurantDTO = RsRestaurantDTO.builder()
                .rsId(restaurant.getRsId())
                .rsName(restaurant.getRsName())
                .rsPhone(restaurant.getRsPhone())
                .rsPark(restaurant.getRsPark())
                .rsTime(restaurant.getRsTime())
                .rsState(restaurant.getRsState())
                .rsReviewCount(restaurant.getRsReviewCount())
                .rsBookmarkCount(restaurant.getRsBookmarkCount())
                .rsAvgRate(restaurant.getRsAvgRate())
                .rsReservationCount(restaurant.getRsReservationCount())
                .build();

        return RsRestaurantDetailDTO.builder()
                .restaurant(restaurantDTO)
                .menuItems(menuItems)
                .coordinates(coordinatesDTO)
                .newsItems(newsItems)
                .amenities(amenities)
                .locationCategory(locationCategoryDTO)
                .reviews(reviews)
                .build();
    }
}
*/
