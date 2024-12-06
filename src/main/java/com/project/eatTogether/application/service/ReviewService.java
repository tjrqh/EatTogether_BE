/*
package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsReviewDTO;
import com.project.eatTogether.infrastructure.RsReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final RsReviewsRepository reviewsRepository;

    public List<RsReviewDTO> getReviewsByRestaurantId(Long restaurantId, int page, int size) {
        return reviewsRepository.findByRsRestaurantRsId(restaurantId, PageRequest.of(page, size))
                .stream()
                .map(review -> RsReviewDTO.builder()
                        .reviewId(review.getRsReviewId())
                        .userId(review.getUser().getUserId())
                        .rsId(review.getRsRestaurant().getRsId())
                        .rsReservationId(review.getRsReservation().getRsReservationId())
                        .reviewContent(review.getRsReviewContent())
                        .reviewRate(review.getRsReviewRate())
                        .reviewCreatedAt(review.getRsReviewCreatedAt())
                        .reviewState(review.getRsReviewState())
                        .reviewLike(review.getRsReviewLike())
                        .build())
                .collect(Collectors.toList());
    }
}
*/
