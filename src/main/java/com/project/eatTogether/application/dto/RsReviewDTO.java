package com.project.eatTogether.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RsReviewDTO {
    private Long reviewId; // 리뷰 ID
    private Long memberId; // 유저 ID
    private Long rsId; // 식당 ID
    private Long rsReservationId; // 예약 ID
    private String reviewContent; // 리뷰 내용
    private Double reviewRate; // 리뷰 평점
    private LocalDateTime createdAt; // 작성일
    private String reviewState; // 리뷰 상태
    private Long reviewLike; // 리뷰 좋아요 수
}