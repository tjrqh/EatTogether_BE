package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsRestaurantDTO;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final RsRestaurantRepository restaurantRepository;

    public List<RsRestaurantDTO> getFilteredRestaurants(String sortBy, String sortOrder, int page, int size, String categoryName) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortOrder) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        List<RsRestaurant> restaurants;

        if (categoryName != null && !categoryName.isEmpty()) {
            restaurants = restaurantRepository.findByCategoryName(categoryName, pageable).getContent();
        } else {
            restaurants = restaurantRepository.findAll(pageable).getContent();
        }

        return restaurants.stream()
                .map(restaurant -> RsRestaurantDTO.builder()
                        .rsId(restaurant.getRsId())
                        .rsName(restaurant.getRsName())
                        .rsPhone(restaurant.getRsPhone())
                        .rsPark(restaurant.getRsPark())
                        .rsTime(restaurant.getRsTime())
                        .rsState(restaurant.getRsState())
                        .rsReviewCount(restaurant.getRsReviewCount())
                        .rsBookmarkCount(restaurant.getRsBookmarkCount())
                        .rsAvgRate(restaurant.getRsAvgRate())
                        .rsReservationCount(restaurant.getRsReservationCount())
                        .build())
                .collect(Collectors.toList());
    }
}
