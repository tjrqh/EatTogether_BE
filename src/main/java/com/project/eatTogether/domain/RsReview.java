package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsReview {

    @Id //식당 리뷰 id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Long rsReviewId;

    @ManyToOne // 유저 id
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @ManyToOne // 식당 id
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rsRestaurant;

    @OneToOne(mappedBy = "rs_review")
    private RsReservation rsReservation;

    @OneToMany(mappedBy = "rsReview")
    @Column(nullable = false)
    private List<RsReviewComment> rsReviewComments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rs_review_comment_id")
    private ReviewDeclare reviewDeclare;

    @Column
    public Byte rsReviewRate;

    @Column(nullable = false)
    public Date rsReviewCreatedAt;

    @Column
    public String rsReviewState;

    @Column
    public long rsReviewLike;

}
