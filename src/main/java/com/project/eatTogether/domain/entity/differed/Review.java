package com.project.eatTogether.domain.entity.differed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.ReviewStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@ToString(exclude = {"member", "restaurant", "comments"})
public class Review extends BaseEntity {

    @Id //식당 리뷰 id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 유저 id
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnore
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)  // 식당 id
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    public Double rating;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewStatus status;

    @Column
    @Builder.Default
    public long like = 0L;

    // 비즈니스 메서드
    public void update(String content, Double rating) {
        this.content = content;
        this.rating = rating;
        updateRestaurantRating();
    }

    public void delete() {
        this.status = ReviewStatus.DELETED;
        updateRestaurantRating();
    }

    public void incrementLike() {
        this.like++;
    }

    public void decrementLike() {
        this.like = Math.max(0, this.like - 1);
    }

    private void updateRestaurantRating() {
        if (restaurant != null) {
            restaurant.updateRating();
        }
    }

}
