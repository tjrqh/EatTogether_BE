package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class RsReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rsReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
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

    // 순환 참조 방지를 위해 @JsonIgnore 추가
    @OneToMany(mappedBy = "rsReview")
    @JsonIgnore
    private List<RsReservation> rsReservations;
}
