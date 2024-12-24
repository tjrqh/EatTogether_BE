package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsRestaurantDetailDTO;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantModifyReadResponse;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantModifyUpdateRequest;
import com.project.eatTogether.application.service.RsRestaurantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant-details")
@RequiredArgsConstructor
public class RsRestaurantDetailController {

    private final RsRestaurantDetailService restaurantDetailService;

    @GetMapping("/{rsId}")
    public RsRestaurantDetailDTO getRestaurantDetails(@PathVariable Long rsId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return restaurantDetailService.getRestaurantDetails(rsId, page, size);
    }

    // admin 식당 정보 수정 초기 화면 렌더링
    @GetMapping("/store")
    public RestaurantModifyReadResponse getRestaurantDetails() {
        Long userId=1L;
        return restaurantDetailService.getRestaurantModifyReadResponse(userId);
    }

    @PutMapping("/store")
    public ResponseEntity<HttpStatus> updateRestaurantDetails(@RequestBody RestaurantModifyUpdateRequest restaurantModifyUpdateRequest) {
        Long rsId=1L;
        return restaurantDetailService.updateRestaurant(restaurantModifyUpdateRequest,rsId);
    }
}