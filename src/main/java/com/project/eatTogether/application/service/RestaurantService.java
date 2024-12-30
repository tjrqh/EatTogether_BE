package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsCoordinatesDTO;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantLocationDto;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantResponseDto;
import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.infrastructure.CuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.RestaurantRepository;
import com.project.eatTogether.infrastructure.security.exception.RestaurantNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CuisineCategoriesRepository cuisineCategoriesRepository;

//    @Transactional
//    public Restaurant createRestaurantWithCategory(OwnerSignUpDto ownerSignUpDto) {
//        // 카테고리 조회
//        CuisineCategories cuisineCategory = cuisineCategoriesRepository.findById(ownerSignUpDto.getCategoryId())
//                .orElseThrow(() -> new EntityNotFoundException("해당 음식 카테고리를 찾을 수 없습니다"));
//
//        // 주소 생성
//        Address address = new Address(
//                ownerSignUpDto.getStreetAddress(),
//                ownerSignUpDto.getDetailAddress(),
//                ownerSignUpDto.getPostcode(),
//                null,
//                null
//        );
//
//        // 레스토랑 생성
//        Restaurant restaurant = Restaurant.createRestaurant(
//                ownerSignUpDto.getRestaurantName(),
//                ownerSignUpDto.getRestaurantPhone(),
//                owner,
//                address,
//                null,  // 이 메서드에서는 Document가 필요없는 경우
//                cuisineCategory
//        );
//
//        return restaurantRepository.save(restaurant);
//    }
//
//        // 카테고리 설정
//        CuisineCategories cuisineCategory = cuisineCategoriesRepository.findById(ownerSignUpDto.getCategoryId())
//                .orElseThrow(() -> new EntityNotFoundException("해당 음식 카테고리를 찾을 수 없습니다"));
//        restaurant.setCuisineCategory(cuisineCategory);
//
//        return restaurantRepository.save(restaurant);
//    }

//    @Override
//    public List<RestaurantResponseDto> findByConditions(String location, String category) {
//        List<RestaurantResponseDto> result = restaurantRepository.searchRestaurants(location, category);
//
//        return result.stream()
//                .map(RestaurantResponseDto::from)
//                .collect(Collectors.toList());
//    }

    public List<RestaurantResponseDto> findAllRsRestaurants() {
        List<RsRestaurant> result = restaurantRepository.findAll();

        return result.stream()
                .map(RestaurantResponseDto::from)
                .collect(Collectors.toList());
    }

    public RsCoordinatesDTO getRestaurantCoordinates(Long rsId) {
        RsRestaurant restaurant = restaurantRepository.findById(rsId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with id: " + rsId));

        RsCoordinates coordinates = restaurant.getRsCoordinates();
        return RsCoordinatesDTO.fromEntity(coordinates);
    }

    public List<RestaurantLocationDto> getAllRestaurantLocations() {
        List<RsRestaurant> restaurants = restaurantRepository.findAllWithCoordinates();
        return restaurants.stream()
                .map(RestaurantLocationDto::fromEntity)
                .collect(Collectors.toList());
    }
}
