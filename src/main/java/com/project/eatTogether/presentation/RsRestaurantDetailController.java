package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsRestaurantDetailDTO;
import com.project.eatTogether.application.service.RsRestaurantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RsRestaurantDetailController {

    private final RsRestaurantDetailService restaurantDetailService;

    @GetMapping("/api/restaurant-details/{restaurantId}")
    public RsRestaurantDetailDTO getRestaurantDetails(@PathVariable Long restaurantId,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        return restaurantDetailService.getRestaurantDetails(restaurantId, page, size);
    }
}
