package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsRestaurantDetailDTO;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantModifyReadResponse;
import com.project.eatTogether.application.service.RsRestaurantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant-details")
@RequiredArgsConstructor
public class RsRestaurantDetailController {

    private final RsRestaurantDetailService restaurantDetailService;

    @GetMapping("/api/restaurant-details/{rsId}")
    public RsRestaurantDetailDTO getRestaurantDetails(@PathVariable Long rsId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return restaurantDetailService.getRestaurantDetails(rsId, page, size);
    }

    //Owner 창 메뉴 불러오기
    @GetMapping("/store")
    public RestaurantModifyReadResponse getRestaurantDetails() {
        Long userId=1L;
        return restaurantDetailService.getRestaurantModifyReadResponse(userId);

    }
}