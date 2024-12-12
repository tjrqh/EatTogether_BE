package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.RsReviewDTO;
import com.project.eatTogether.infrastructure.RsReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviesdetailService {

    private final RsReviewsRepository reviewsRepository;

    public List<RsReviewDTO> getReviewsByRestaurantId(Long rsId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewsRepository.findByRsRestaurantRsId(rsId, pageable)
                .stream()
                .map(review -> RsReviewDTO.builder()
                        .reviewId(review.getRsReviewId())
                        .userId(review.getUser().getUserId())
                        .rsId(review.getRsRestaurant().getRsId())
                        .reviewContent(review.getRsReviewContent())
                        .reviewRate(review.getRsReviewRate())
                        .reviewCreatedAt(review.getRsReviewCreatedAt())
                        .reviewState(review.getRsReviewState())
                        .reviewLike(review.getRsReviewLike())
                        .build())
                .collect(Collectors.toList());
    }
}
