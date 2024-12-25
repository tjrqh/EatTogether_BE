package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.*;
import com.project.eatTogether.application.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dining")
public class DiningController {

    private final MenuService menuService;
    private final ReviewDetailService reviewService;
    private final AmenitiesService amenitiesService;
    private final RestaurantInfoService restaurantInfoService;

    @GetMapping("/{rsId}/menus")
    public List<RsMenusDTO> getMenusByRestaurantId(@PathVariable Long rsId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return menuService.getMenusByRestaurantId(rsId, page, size);
    }

    @GetMapping("/{rsId}/reviews")
    public List<WriteRsReviewDTO> getReviewsByRestaurantId(@PathVariable Long rsId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        return reviewService.getReviewsByRestaurantId(rsId, page, size);
    }

    @GetMapping("/{rsId}/amenities")
    public List<RsAmenitiesDTO> getAmenitiesByRestaurantId(@PathVariable Long rsId) {
        return amenitiesService.getAmenitiesByRestaurantId(rsId);
    }

    @GetMapping("/{rsId}/details")
    public List<RsRestaurantInfoDTO> getDetailsByRestaurantId(@PathVariable Long rsId) {
        return restaurantInfoService.getDetailsByRestaurantId(rsId);
    }

}