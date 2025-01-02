package com.project.eatTogether.domain.entity.differed;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.CommentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommentStatus status;

    // 계층형 댓글을 위한 필드
    @Column
    private Long parentId; // 부모 댓글 ID

    @Column(nullable = false)
    private Long commentDepth; // 댓글 깊이 (0: 원댓글, 1: 답글)


    // 정적 팩토리 메서드 - 원댓글 생성
    public static ReviewComment createComment(Review review, Member member, String content) {
        return ReviewComment.builder()
                .review(review)
                .member(member)
                .content(content)
                .status(CommentStatus.ACTIVE)
                .commentDepth(0L)
                .build();
    }


    // 정적 팩토리 메서드 - 답글 생성
    public static ReviewComment createReply(Review review, Member member,
                                            String content, Long parentId) {
        return ReviewComment.builder()
                .review(review)
                .member(member)
                .content(content)
                .status(CommentStatus.ACTIVE)
                .parentId(parentId)
                .commentDepth(1L)
                .build();
    }

    // 비즈니스 메서드
    public void update(String rsCommentContent) {
        this.content = rsCommentContent;
    }

    public void delete() {
        this.status = CommentStatus.DELETED;
    }

    public boolean isReply() {
        return this.commentDepth > 0;
    }
}