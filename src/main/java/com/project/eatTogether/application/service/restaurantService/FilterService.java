package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.application.dto.FilterDTO;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    private final RsRestaurantRepository rsRestaurantRepository;

    public FilterService(RsRestaurantRepository rsRestaurantRepository) {
        this.rsRestaurantRepository = rsRestaurantRepository;
    }

    public Page<FilterDTO> filterRestaurants(String filterType, String sortBy, int page, int size) {
        Page<RsRestaurant> restaurantPage;

        // 필터링 기준에 따른 분기 처리
        switch (filterType.toLowerCase()) {
            case "reviewcount":
                // 리뷰 개수 기준으로 정렬
                restaurantPage = "desc".equalsIgnoreCase(sortBy)
                        ? rsRestaurantRepository.findByReviewCountDesc(PageRequest.of(page, size))
                        : rsRestaurantRepository.findByReviewCountAsc(PageRequest.of(page, size));
                break;
            case "avgrate":  // 평점 기준으로 정렬
                restaurantPage = "desc".equalsIgnoreCase(sortBy)
                        ? rsRestaurantRepository.findByAvgRateDesc(PageRequest.of(page, size))
                        : rsRestaurantRepository.findByAvgRateAsc(PageRequest.of(page, size));
                break;
            case "bookmarkcount":
                // 북마크 개수 기준으로 정렬
                restaurantPage = "desc".equalsIgnoreCase(sortBy)
                        ? rsRestaurantRepository.findByBookmarkCountDesc(PageRequest.of(page, size))
                        : rsRestaurantRepository.findByBookmarkCountAsc(PageRequest.of(page, size));
                break;
            default:
                // 잘못된 filterType 처리
                throw new IllegalArgumentException("Invalid filterType: " + filterType);
        }

        // RsRestaurant 엔티티를 FilterDTO로 변환 후 반환
        return restaurantPage.map(this::convertToDTO);
    }

    // 엔티티 -> DTO 변환 로직
    private FilterDTO convertToDTO(RsRestaurant rsRestaurant) {
        return FilterDTO.builder()
                .rsId(rsRestaurant.getRsId())
                .rsName(rsRestaurant.getRsName())
                .rsReviewCount(rsRestaurant.getRsReviewCount())
                .rsAvgRate(rsRestaurant.getRsAvgRate())  // 평점
                .rsBookmarkCount(rsRestaurant.getRsBookmarkCount())  // 북마크 개수
                .build();
    }
}
