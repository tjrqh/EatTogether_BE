//package com.project.eatTogether.application.service;
//
//import com.project.eatTogether.domain.entity.RsRestaurant;
//import com.project.eatTogether.infrastructure.RsRestaurantRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class RsRestaurantFilterService {
//
//    private final RsRestaurantRepository rsRestaurantRepository;
//
//    public List<RsRestaurant> filterRestaurants(String filterType, String sortBy, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//
//        if ("reviewCount".equals(filterType)) {
//            return "asc".equals(sortBy)
//                    ? rsRestaurantRepository.findByReviewCountAsc(pageable)
//                    : rsRestaurantRepository.findByReviewCountDesc(pageable);
//        } else if ("avgRating".equals(filterType)) {
//            return "asc".equals(sortBy)
//                    ? rsRestaurantRepository.findByAvgRatingAsc(pageable)
//                    : rsRestaurantRepository.findByAvgRatingDesc(pageable);
//        } else {
//            throw new IllegalArgumentException("Invalid filterType: " + filterType);
//        }
//    }
//}