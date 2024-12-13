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

    private Long rsParentCommentId; // 부모 댓글의 id
    private Long rsReviewId;
    private Long userId; // 유저 ID
    private Long rsId; // 식당 ID

     public RsReviewComment toEntity(RsReview rsReview, User user,
                                            RsRestaurant rsRestaurant) {
        return RsReviewComment.builder()
                .rsCommentId(rsCommentId)
                .rsCommentContent(rsCommentContent)
                .rsCommentState(rsCommentState)
                .rsCommentCreatedAt(rsCommentCreatedAt)
                .rsCommentUpdatedAt(rsCommentUpdatedAt)
                .rsCommentDeletedAt(rsCommentDeletedAt)
                .rsCommentDepth(rsCommentDepth)
                .rsParentCommentId(rsParentCommentId)
                .rsReview(rsReview)
                .rsRestaurant(rsRestaurant)
                .user(user)
                .build();
    }
}
