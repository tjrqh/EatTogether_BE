/*
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.*;
import com.project.eatTogether.domain.*;
import com.project.eatTogether.infrastructure.*;
import lombok.RequiredArgsConstructor;
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

    public RsRestaurantDetailDTO getRestaurantDetails(Long restaurantId) {
        RsRestaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        List<RsMenuDTO> menuItems = menuRepository.findByRsId(restaurantId)
                .stream()
                .map(menu -> RsMenuDTO.builder()
                        .menuId(menu.getMenuId())
                        .rsId(menu.getRsId())
                        .itemName(menu.getItemName())
                        .itemDesc(menu.getItemDesc())
                        .itemPrice(menu.getItemPrice())
                        .itemState(menu.getItemState())
                        .itemAppear(menu.getItemAppear())
                        .itemPhotoOrigin(menu.getItemPhotoOrigin())
                        .itemPhotoPath(menu.getItemPhotoPath())
                        .itemPhotoName(menu.getItemPhotoName())
                        .itemCreatedAt(menu.getItemCreatedAt())
                        .itemUpdatedAt(menu.getItemUpdatedAt())
                        .itemDeletedAt(menu.getItemDeletedAt())
                        .build())
                .collect(Collectors.toList());

        RsCoordinates coordinates = coordinatesRepository.findByRsId(restaurantId);
        RsCoordinatesDTO coordinatesDTO = RsCoordinatesDTO.builder()
                .coordinatesId(coordinates.getCoordinatesId())
                .rsId(coordinates.getRsId())
                .latitude(coordinates.getLatitude())
                .longitude(coordinates.getLongitude())
                .build();

        List<RsNewsDTO> newsItems = newsRepository.findByRsId(restaurantId)
                .stream()
                .map(news -> RsNewsDTO.builder()
                        .newsId(news.getNewsId())
                        .rsId(news.getRsId())
                        .newsContent(news.getNewsContent())
                        .newsCreatedAt(news.getNewsCreatedAt())
                        .build())
                .collect(Collectors.toList());

        List<Long> amenitiesIds = amenitiesMappingRepository.findByRsId(restaurantId)
                .stream().map(RsRestaurantAmenitiesMapping::getAmenityId).collect(Collectors.toList());
        List<RsAmenitiesDTO> amenities = amenitiesIds.stream()
                .map(id -> RsAmenitiesDTO.builder()
                        .rsAmenityId(amenitiesRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Amenity not found"))
                                .getrsAmenityId())
                        .amenityName(amenitiesRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Amenity not found"))
                                .getrsAmenityName())
                        .build())
                .collect(Collectors.toList());

        RsLocationCategories locationCategory = locationCategoriesRepository.findByRsId(restaurantId);
        RsLocationCategoriesDTO locationCategoryDTO = RsLocationCategoriesDTO.builder()
                .locationCategoryId(locationCategory.getLocationCategoryId())
                .rsId(locationCategory.getRsId())
                .categoryName(locationCategory.getCategoryName())
                .build();

        List<RsReviewDTO> reviews = reviewsRepository.findByRsId(restaurantId)
                .stream()
                .map(review -> RsReviewDTO.builder()
                        .reviewId(review.getReviewId())
                        .userId(review.getUserId())
                        .rsId(review.getRsId())
                        .rsReservationId(review.getRsReservationId())
                        .reviewContent(review.getReviewContent())
                        .reviewRate(review.getReviewRate())
                        .reviewCreatedAt(review.getReviewCreatedAt())
                        .reviewState(review.getReviewState())
                        .reviewLike(review.getReviewLike())
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
