package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.FilterDTO;
import com.project.eatTogether.application.service.restaurantService.FilterService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
public class FilterController {

    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<FilterDTO>> filterRestaurants(
            @RequestParam String filterType,
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        // 필터링 타입에 따라 서비스 호출
        Page<FilterDTO> filteredRestaurants = filterService.filterRestaurants(filterType, sortBy, page, size);
        return ResponseEntity.ok(filteredRestaurants);
    }

}
