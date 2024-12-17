package com.project.eatTogether.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class RsReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rsCommentId;

    @ManyToOne
    @JoinColumn(name = "rs_review_id" ,nullable = false)
    public RsReview rsReview;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rsRestaurant;

    @Column(nullable = false)
    public String rsCommentContent;

    @Column
    public String rsCommentState;

    @Column(nullable = false)
    public LocalDateTime rsCommentCreatedAt;

    @Column
    public LocalDateTime rsCommentUpdatedAt;

    @Column
    public LocalDateTime rsCommentDeletedAt;

    //부모 댓글 정의
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_parent_comment_id")
    public RsReviewComment parentComment; // 부모 댓글 참조
    // 자식 댓글 정의
    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
    public List<RsReviewComment> childComments = new ArrayList<>();

    @Column
    public Long rsCommentDepth;

}