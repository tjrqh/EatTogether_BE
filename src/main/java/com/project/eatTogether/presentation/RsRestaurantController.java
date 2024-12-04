package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.application.dto.RsRestaurantDTO;
import com.project.eatTogether.application.service.RsCuisineCategoriesService;
import com.project.eatTogether.application.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RsRestaurantController {

    private final RsCuisineCategoriesService cuisineCategoriesService;
    private final FilterService filterService;

    @GetMapping("/cuisine-category")
    public RsCuisineCategoriesDTO getCuisineCategory(@RequestParam String categoryName) {
        return cuisineCategoriesService.getCuisineCategoryByName(categoryName);
    }

    @GetMapping("/filter")
    public List<RsRestaurantDTO> getFilteredRestaurants(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String categoryName) {

        return filterService.getFilteredRestaurants(sortBy, sortOrder, page, size, categoryName);
    }
}
