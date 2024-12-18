//package com.project.eatTogether.presentation;
//
//import com.project.eatTogether.application.dto.RsRestaurantDTO;
//import com.project.eatTogether.application.service.RsRestaurantFilterService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/restaurants")
//@RequiredArgsConstructor
//public class RsRestaurantFilterController {
//
//    private final RsRestaurantFilterService service;
//
//    @GetMapping("/filter")
//    public ResponseEntity<List<RsRestaurantDTO>> filterRestaurants(
//            @RequestParam String filterType,  // reviewCount 또는 avgRating
//            @RequestParam String sortBy,      // asc 또는 desc
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        // Service에서 데이터 가져오기
//        List<RsRestaurantDTO> restaurants = service.filterRestaurants(filterType, sortBy, page, size);
//
//        // 데이터 응답
//        return ResponseEntity.ok(restaurants);
//    }
//}
