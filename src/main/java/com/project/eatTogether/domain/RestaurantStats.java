package com.project.eatTogether.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RestaurantStats {
    @Column
    @Builder.Default
    private int reviewCount = 0;       // 리뷰 수

    @Column
    @Builder.Default
    private int bookmarkCount = 0;     // 북마크 수

    @Column
    @Builder.Default
    private int reservationCount = 0;  // 예약 수

    public void incrementReviewCount() {
        this.reviewCount++;
    }

    public void incrementBookmarkCount() {
        this.bookmarkCount++;
    }

    public void incrementReservationCount() {
        this.reservationCount++;
    }
}
