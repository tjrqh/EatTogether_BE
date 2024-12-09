package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.application.service.RsCuisineCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RsRestaurantController {

    private final RsCuisineCategoriesService cuisineCategoriesService;

    @GetMapping("/cuisine-category")
    public List<RsCuisineCategoriesDTO> getCuisineCategory(@RequestParam String categoryName,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return cuisineCategoriesService.getCuisineCategoryByName(categoryName, page, size);
    }
}
