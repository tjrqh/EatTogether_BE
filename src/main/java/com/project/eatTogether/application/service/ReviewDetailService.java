//package com.project.eatTogether.application.service;
//
//import com.project.eatTogether.application.dto.WriteRsReviewDTO;
//import com.project.eatTogether.infrastructure.RsReviewsRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class ReviewDetailService {
//
//    private final RsReviewsRepository reviewsRepository;
//
//    public List<WriteRsReviewDTO> getReviewsByRestaurantId(Long rsId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        return reviewsRepository.findByRsRestaurantRsId(rsId, pageable)
//                .stream()
//                .map(review -> WriteRsReviewDTO.builder()
//                        .rsReviewId(review.getRsReviewId())
//                        .userId(review.getUser().getUserId())
//                        .rsId(review.getRsRestaurant().getRsId())
//                        .rsReviewContent(review.getRsReviewContent())
//                        .rsReviewRate(review.getRsReviewRate())
//                        .createdAt(review.getCreatedAt())
//                        .rsReviewState(review.getRsReviewState())
//                        .rsReviewLike(review.getRsReviewLike())
//                        .build())
//                .collect(Collectors.toList());
//    }
//}
