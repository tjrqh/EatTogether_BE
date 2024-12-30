package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.application.dto.RsLocationCategoriesDTO;
import com.project.eatTogether.application.dto.RsRestaurantDTO;
import com.project.eatTogether.application.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search/name")
    public List<RsRestaurantDTO> searchByName(@RequestParam String query,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return searchService.searchByName(query, page, size);
    }

    @GetMapping("/search/category")
    public List<RsCuisineCategoriesDTO> searchByCuisineCategory(@RequestParam String categoryName,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        return searchService.searchByCuisineCategory(categoryName, page, size);
    }

    @GetMapping("/search/location")
    public List<RsLocationCategoriesDTO> searchByLocation(@RequestParam String locationName,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return searchService.searchByLocation(locationName, page, size);
    }
}
