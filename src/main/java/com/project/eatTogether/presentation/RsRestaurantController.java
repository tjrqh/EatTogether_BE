package com.project.eatTogether.presentation;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.application.dto.RsRestaurantMapReadResponse;
import com.project.eatTogether.application.service.RsCuisineCategoriesService;
import com.project.eatTogether.application.service.RsRestaurantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RsRestaurantController {

    private final RsCuisineCategoriesService cuisineCategoriesService;
    private final RsRestaurantDetailService restaurantDetailService;

    @GetMapping("/cuisine-category")
    public List<RsCuisineCategoriesDTO> getCuisineCategory(@RequestParam String categoryName,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return cuisineCategoriesService.getCuisineCategoryByName(categoryName, page, size);
    }

    @GetMapping("/all_thousand_addr")
    public List<RsRestaurantMapReadResponse> getRestaurantMapAddress(){
        Long id = 1L;
        return restaurantDetailService.getRestaurantMapAddress(id);
    }

    @PutMapping("/close")
    public ResponseEntity<HttpStatus> closeRestaurant(@RequestParam String state) {
        Long id = 1L;
        return restaurantDetailService.deleteRestaurant(id, state);
    }
}
