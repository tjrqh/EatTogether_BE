package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class RsReview {

    @Id //식당 리뷰 id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rsReviewId;

    @ManyToOne(fetch = FetchType.LAZY) // 유저 id
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @ManyToOne(fetch = FetchType.LAZY) // 식당 id
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "rsReview")
    @Column(nullable = false)
    private List<RsReviewComment> rsReviewComments;

    @Column(nullable = false)
    private String rsReviewContent;

    @Column
    public Byte rsReviewRate;

    @Column(nullable = false)
    public LocalDateTime rsReviewCreatedAt;

    @Column
    public LocalDateTime rsReviewUpdatedAt;

    @Column
    public LocalDateTime rsReviewDeletedAt;

    @Column
    public String rsReviewState;

    @Column
    public long rsReviewLike;

}
