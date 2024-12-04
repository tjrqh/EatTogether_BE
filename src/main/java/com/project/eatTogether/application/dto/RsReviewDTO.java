package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsReviewDTO {
    private Long reviewId; // 리뷰 id
    private Long userId; // 유저 id
    private Long rsId; // 식당 id
    private Long rsReservationId; // 예약 id
    private String reviewContent; // 리뷰 내용
    private Byte reviewRate; // 리뷰 평점
    private String reviewCreatedAt; // 작성일
    private String reviewState; // 리뷰 상태
    private Long reviewLike; // 리뷰 좋아요 수
}
