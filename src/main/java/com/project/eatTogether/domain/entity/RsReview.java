package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.domain.enums.ReviewStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class RsReview extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rsReviewId;

    @Column
    public Double rsReviewRate;

    @Column
    public String rsReviewState;

    @Column
    @Builder.Default
    public long rsReviewLike = 0L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
    public RsRestaurant rsRestaurant;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String rsReviewContent;

    @OneToMany(mappedBy = "rsReview", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<RsReviewComment> rsReviewComments;

//    // 순환 참조 방지를 위해 @JsonIgnore 추가
//    @OneToMany(mappedBy = "rsReview")
//    @JsonIgnore
//    private List<RsReservation> rsReservations;

    // 비즈니스 메서드
    public void update(String rsReviewContent, Double rsReviewRate) {
        this.rsReviewContent = rsReviewContent;
        this.rsReviewRate = rsReviewRate;
        updateRestaurantRating();
    }

    public void delete() {
        this.status = ReviewStatus.DELETED;
        updateRestaurantRating();
    }

    public void incrementLike() {
        this.rsReviewLike++;
    }

    public void decrementLike() {
        this.rsReviewLike = Math.max(0, this.rsReviewLike - 1);
    }

    private void updateRestaurantRating() {
        if (rsRestaurant != null) {
            rsRestaurant.updateRating();
        }
    }
}
