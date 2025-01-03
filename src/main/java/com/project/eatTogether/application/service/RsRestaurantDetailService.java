
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsAmenitiesDTO;
import com.project.eatTogether.application.dto.RsLocationCategoriesDTO;
import com.project.eatTogether.application.dto.RsMenusDTO;
import com.project.eatTogether.application.dto.RsNewsDTO;
import com.project.eatTogether.application.dto.RsRestaurantDTO;
import com.project.eatTogether.application.dto.RsRestaurantDetailDTO;
import com.project.eatTogether.application.dto.RsRestaurantMapReadResponse;
import com.project.eatTogether.application.dto.RsReviewDTO;
import com.project.eatTogether.application.dto.differed.coordinates.RsCoordinatesDto;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantModifyReadResponse;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantModifyUpdateRequest;
import com.project.eatTogether.domain.entity.RsAmenities;
import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.domain.entity.RsLocationCategories;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.infrastructure.RsAmenitiesRepository;
import com.project.eatTogether.infrastructure.RsCoordinatesRepository;
import com.project.eatTogether.infrastructure.RsLocationCategoriesRepository;
import com.project.eatTogether.infrastructure.RsMenuRepository;
import com.project.eatTogether.infrastructure.RsNewsRepository;
import com.project.eatTogether.infrastructure.RsRestaurantAmenitiesMappingRepository;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import com.project.eatTogether.infrastructure.RsReviewsRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    List<RsMenusDTO> menuItems = menuRepository.findByRsRestaurantRsId(rsId,
            PageRequest.of(page, size))
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
            .createdAt(menu.getCreatedAt())
            .modifiedAt(menu.getModifiedAt())
            .deletedAt(menu.getDeletedAt())
            .build())
        .collect(Collectors.toList());

    RsCoordinates coordinates = coordinatesRepository.findByRsRestaurantRsId(rsId);
    RsCoordinatesDto coordinatesDTO = RsCoordinatesDto.builder()
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
            .createdAt(news.getCreatedAt())
            .modifiedAt(news.getModifiedAt())
            .deletedAt(news.getModifiedAt())
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

    RsLocationCategories locationCategory = locationCategoriesRepository.findByRsRestaurantRsId(
        rsId);
    RsLocationCategoriesDTO locationCategoryDTO = RsLocationCategoriesDTO.builder()
        .rsLocationId(locationCategory.getRsLocationId())
        .rsId(locationCategory.getRsRestaurant().getRsId())
        .rsLocationName(locationCategory.getRsLocationName())
        .build();

    List<RsReviewDTO> reviews = reviewsRepository.findByRsRestaurantRsId(rsId,
            PageRequest.of(page, size))
        .stream()
        .map(review -> RsReviewDTO.builder()
            .reviewId(review.getRsReviewId())
            .memberId(review.getMember().getId())
            .rsId(review.getRsRestaurant().getRsId())
            .reviewContent(review.getRsReviewContent())
            .reviewRate(review.getRsReviewRate())
            .createdAt(review.getCreatedAt())
            .reviewState(review.getRsReviewState())
            .reviewLike(review.getRsReviewLike())
            .build())
        .collect(Collectors.toList());

    RsRestaurantDTO restaurantDTO = RsRestaurantDTO.builder()
        .rsId(restaurant.getRsId())
        .rsName(restaurant.getRsName())
        .rsPhone(restaurant.getRsPhone())
        .rsTime(restaurant.getRsTime())
        .rsState(restaurant.getRsState())
        .rsReviewCount(restaurant.getRsReviewCount())
        .rsBookmarkCount(restaurant.getRsBookmarkCount())
        .rsAvgRate(restaurant.getRsAvgRate())
        .rsReservationCount(restaurant.getRsReservationCount())
        .rsInfo(restaurant.getRsInfo())
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

  // admin 식당 정보 수정 초기 화면 렌더링
  public RestaurantModifyReadResponse getRestaurantModifyReadResponse(Long userId) {
    Optional<RsRestaurant> rsRestaurant = restaurantRepository.findById(
        userId); // Optional이 아니라 객체가 직접 반환됨
    RsRestaurant restaurant = rsRestaurant.orElseThrow(() ->
        new RuntimeException("Restaurant not found : " + userId));
    if (restaurant == null) {
      return null;  // 식당이 없으면 null 반환
    }

    return RestaurantModifyReadResponse
        .builder()
        .id(restaurant.getRsId())
        .name(restaurant.getRsName())
        .description(restaurant.getRsInfo())
        .address(restaurant.getRsCoordinates().getRestaurantAddr())
        .contact(restaurant.getRsPhone())
        .build();
  }

  @Transactional
  public ResponseEntity<HttpStatus> updateRestaurant(
      RestaurantModifyUpdateRequest restaurantModifyUpdateRequest, Long rsId) {
    try {
      Optional<RsRestaurant> rsRestaurant = restaurantRepository.findById(rsId);
      RsRestaurant restaurant = rsRestaurant.orElseThrow(() ->
          new RuntimeException("Restaurant not found : " + rsId));
      restaurant.setRsName(restaurantModifyUpdateRequest.getName());
      restaurant.setRsInfo(restaurantModifyUpdateRequest.getDescription());
      restaurant.getRsCoordinates().setRestaurantAddr(restaurantModifyUpdateRequest.getAddress());
      restaurant.setRsPhone(restaurantModifyUpdateRequest.getContact());
      restaurantRepository.save(restaurant);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid state value: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (
        DataAccessException e) {
      System.out.println("Database error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      System.out.println("Unexpected error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Unexpected error occurred", e);
    }
  }

  @Transactional
  public List<RsRestaurantMapReadResponse> getRestaurantMapAddress(Long id) {
    return restaurantRepository.findByRsId(id).stream().map(map -> RsRestaurantMapReadResponse
        .builder()
        .id(map.getRsId())
        .name(map.getRsName())
        .address(map.getRsCoordinates().getRestaurantAddr())
        .phone(map.getRsPhone())
        .build())
        .collect(Collectors.toList());
  }

  public ResponseEntity<HttpStatus> deleteRestaurant(Long rsId, String state) {
    Optional<RsRestaurant> rsRestaurant = restaurantRepository.findById(rsId);
    RsRestaurant restaurant = rsRestaurant.orElseThrow(() ->
            new RuntimeException("Restaurant not found : " + rsId));
    try {
      restaurant.setRsState(state);
      restaurant.delete();  // delete() 메서드 사용
      restaurantRepository.save(restaurant);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid state value: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (DataAccessException e) {
      System.out.println("Database error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      System.out.println("Unexpected error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
              "Unexpected error occurred", e);
    }
  }
}
