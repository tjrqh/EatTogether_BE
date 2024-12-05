package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.application.dto.RsLocationCategoriesDTO;
import com.project.eatTogether.application.service.RsCuisineCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RsRestaurantController {

    private final RsCuisineCategoriesService cuisineCategoriesService;

    @GetMapping("/cuisine-category")
    public RsCuisineCategoriesDTO getCuisineCategory(@RequestParam String categoryName) {
        return cuisineCategoriesService.getCuisineCategoryByName(categoryName);
    }
}
