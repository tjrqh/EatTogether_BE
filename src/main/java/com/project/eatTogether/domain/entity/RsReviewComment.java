package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
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
public class RsReviewComment extends BaseEntity {

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

    @Column(nullable = false, columnDefinition = "TEXT")
    public String rsCommentContent;

    @Column
    public String rsCommentState;

//    //부모 댓글 정의
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "rs_parent_comment_id")
//    public RsReviewComment parentComment; // 부모 댓글 참조
//    // 자식 댓글 정의
//    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
//    public List<RsReviewComment> childComments = new ArrayList<>();
//
//    @Column
//    public Long rsCommentDepth;
//
//
//    // 계층형 댓글을 위한 필드
//    @Column
//    private Long parentId; // 부모 댓글 ID
//
//    @Column(nullable = false)
//    private Long commentDepth; // 댓글 깊이 (0: 원댓글, 1: 답글)
//
//
//    // 정적 팩토리 메서드 - 원댓글 생성
//    public static ReviewComment createComment(Review review, Member member, String content) {
//        return ReviewComment.builder()
//                .review(review)
//                .member(member)
//                .content(content)
//                .status(CommentStatus.ACTIVE)
//                .commentDepth(0L)
//                .build();
//    }
//
//
//    // 정적 팩토리 메서드 - 답글 생성
//    public static ReviewComment createReply(Review review, Member member,
//                                            String content, Long parentId) {
//        return ReviewComment.builder()
//                .review(review)
//                .member(member)
//                .content(content)
//                .status(CommentStatus.ACTIVE)
//                .parentId(parentId)
//                .commentDepth(1L)
//                .build();
//    }
//
//    // 비즈니스 메서드
//    public void update(String rsCommentContent) {
//        this.content = rsCommentContent;
//    }
//
//    public void delete() {
//        this.status = CommentStatus.DELETED;
//    }
//
//    public boolean isReply() {
//        return this.commentDepth > 0;
//    }

}