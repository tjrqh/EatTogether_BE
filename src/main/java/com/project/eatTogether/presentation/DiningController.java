package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.*;
import com.project.eatTogether.application.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dining")
public class DiningController {

    private final MenuService menuService;
    private final ReviewService reviewService;
    private final AmenitiesService amenitiesService;

    @GetMapping("/{restaurantId}/menus")
    public List<RsMenusDTO> getMenusByRestaurantId(@PathVariable Long restaurantId,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        return menuService.getMenusByRestaurantId(restaurantId, page, size);
    }

    @GetMapping("/{restaurantId}/reviews")
    public List<RsReviewDTO> getReviewsByRestaurantId(@PathVariable Long restaurantId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return reviewService.getReviewsByRestaurantId(restaurantId, page, size);
    }

    @GetMapping("/{restaurantId}/amenities")
    public List<RsAmenitiesDTO> getAmenitiesByRestaurantId(@PathVariable Long restaurantId) {
        return amenitiesService.getAmenitiesByRestaurantId(restaurantId);
    }
}
