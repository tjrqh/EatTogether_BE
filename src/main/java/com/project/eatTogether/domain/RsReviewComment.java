package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @Column
    public Long rsParentCommentId;

    @Column
    public Long rsCommentDepth;

}