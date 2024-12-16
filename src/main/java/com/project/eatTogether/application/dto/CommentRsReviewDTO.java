package com.project.eatTogether.application.dto;

import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.domain.RsReview;
import com.project.eatTogether.domain.RsReviewComment;
import com.project.eatTogether.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRsReviewDTO {

    private Long rsCommentId;
    private String rsCommentContent;
    private String rsCommentState;
    private LocalDateTime rsCommentCreatedAt;
    private LocalDateTime rsCommentUpdatedAt;
    private LocalDateTime rsCommentDeletedAt;
    private Long rsCommentDepth;

    private Long parentCommentId; // 부모 댓글의 id
    private Long rsReviewId;
    private Long userId; // 유저 ID
    private Long rsId; // 식당 ID

     public RsReviewComment toEntity(RsReview rsReview, User user,
                                            RsRestaurant rsRestaurant, RsReviewComment parentComment) {
        return RsReviewComment.builder()
                .rsCommentId(rsCommentId)
                .rsCommentContent(rsCommentContent)
                .rsCommentState(rsCommentState)
                .rsCommentCreatedAt(rsCommentCreatedAt != null ? this.rsCommentCreatedAt : LocalDateTime.now())
                .rsCommentUpdatedAt(rsCommentUpdatedAt != null ? this.rsCommentUpdatedAt : LocalDateTime.now())
                .rsCommentDeletedAt(rsCommentDeletedAt != null ? this.rsCommentDeletedAt : LocalDateTime.now())
                .rsCommentDepth(rsCommentDepth)
                .parentComment(parentComment) // 부모 댓글 참조 설정)
                .rsReview(rsReview)
                .rsRestaurant(rsRestaurant)
                .user(user)
                .build();
    }
}
