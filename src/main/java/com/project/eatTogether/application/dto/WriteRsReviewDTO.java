    package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.RsReview;
import com.project.eatTogether.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

    @Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteRsReviewDTO {

    private Long rsReviewId; // 리뷰 ID
    private String rsReviewContent; // 리뷰 내용
    private Double rsReviewRate; // 리뷰 평점
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일
    private LocalDateTime deletedAt; // 삭제일
    private String rsReviewState; // 리뷰 상태
    private Long rsReviewLike; // 리뷰 좋아요 수
    private Long userId; // 유저 ID
    private Long rsId; // 식당 ID

    public RsReview toEntity(RsRestaurant rsRestaurant, User user) {
        return RsReview.builder()
                .rsReviewId(rsReviewId)
                .rsReviewContent(rsReviewContent)
                .rsReviewRate(rsReviewRate)
                .rsReviewState(rsReviewState)
                .rsReviewLike(rsReviewLike)
                .rsRestaurant(rsRestaurant)
                .user(user)
                .build();
    }
}
