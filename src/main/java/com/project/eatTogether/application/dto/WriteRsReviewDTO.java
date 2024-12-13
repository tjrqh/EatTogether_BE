    package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.domain.RsReview;
import com.project.eatTogether.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteRsReviewDTO {

    private Long rsReviewId; // 리뷰 ID
    private String rsReviewContent; // 리뷰 내용
    private Byte rsReviewRate; // 리뷰 평점
    private LocalDateTime rsReviewCreatedAt; // 작성일
    private LocalDateTime rsReviewUpdatedAt; // 수정일
    private LocalDateTime rsReviewDeletedAt; // 삭제일
    private String rsReviewState; // 리뷰 상태
    private Long rsReviewLike; // 리뷰 좋아요 수
    private Long userId; // 유저 ID
    private Long rsId; // 식당 ID

    public RsReview toEntity(RsRestaurant rsRestaurant, User user) {
        return RsReview.builder()
                .rsReviewId(rsReviewId)
                .rsReviewContent(rsReviewContent)
                .rsReviewRate(rsReviewRate)
                .rsReviewCreatedAt(rsReviewCreatedAt != null ? this.rsReviewCreatedAt : LocalDateTime.now())
                .rsReviewUpdatedAt(rsReviewUpdatedAt != null ? this.rsReviewUpdatedAt : LocalDateTime.now())
                .rsReviewDeletedAt(rsReviewDeletedAt != null ? this.rsReviewDeletedAt : LocalDateTime.now())
                .rsReviewState(rsReviewState)
                .rsReviewLike(rsReviewLike)
                .rsRestaurant(rsRestaurant)
                .user(user)
                .build();
    }
}
