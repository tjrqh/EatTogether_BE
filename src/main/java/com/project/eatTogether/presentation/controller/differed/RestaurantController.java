package com.project.eatTogether.presentation.controller.differed;

import com.project.eatTogether.application.dto.differed.coordinates.RestaurantLocationDto;
import com.project.eatTogether.application.dto.differed.coordinates.RsCoordinatesDto;
import com.project.eatTogether.application.dto.differed.restaurant.CategoryDto;
import com.project.eatTogether.application.dto.differed.restaurant.RestaurantResponseDto;
import com.project.eatTogether.application.service.differed.CuisineCategoryService;
import com.project.eatTogether.application.service.differed.RestaurantService;
import com.project.eatTogether.domain.enums.CuisineType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final CuisineCategoryService cuisineCategoryService;

    //전체식당조회
    @GetMapping
    public ResponseEntity<?> getAllRestaurants() {
        try {
            List<RestaurantResponseDto> restaurants = restaurantService.findAllRsRestaurants();
            return ResponseEntity.ok(restaurants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{rsId}/coordinates")
    public ResponseEntity<RsCoordinatesDto> getRestaurantCoordinates(@PathVariable Long rsId) {
        RsCoordinatesDto coordinates = restaurantService.getRestaurantCoordinates(rsId);
        return ResponseEntity.ok(coordinates);
    }

    @GetMapping("/all-coordinates")
    public ResponseEntity<List<RestaurantLocationDto>> getAllRestaurantCoordinates() {
        List<RestaurantLocationDto> locations = restaurantService.getAllRestaurantLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/categories")
public ResponseEntity<?> getAllCategories() {
        List<CategoryDto> categories = Arrays.stream(CuisineType.values())
                .map(type -> CategoryDto.builder()
                        .id(Long.valueOf(type.ordinal()))  // enum의 순서를 ID로 사용
                        .name(type.getDisplayName())       // 표시될 이름 (한글명)
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(categories);
    }
}
