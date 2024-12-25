
package com.project.eatTogether.application.service.reviewService;


import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.RsReview;
import com.project.eatTogether.domain.entity.RsReviewComment;
import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.infrastructure.repository.CommentReviewRepository;
import com.project.eatTogether.infrastructure.repository.UserRepository;
import com.project.eatTogether.infrastructure.repository.WriteReviewRepository;
import com.project.eatTogether.infrastructure.repository.WriteRsRestaurantRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class    CommentReviewService {

    private static final Logger logger = LoggerFactory.getLogger(CommentReviewService.class);

    private final CommentReviewRepository commentReviewRepository;
    private final WriteReviewRepository writeReviewRepository;
    private final UserRepository userRepository;
    private final WriteRsRestaurantRepository writeRsRestaurantRepository;

    // 댓글 작성 메서드
    public RsReviewComment save(CommentRsReviewDTO commentRsReviewDTO) {
        logger.debug("Received CommentRsReviewDTO: {}", commentRsReviewDTO);

        if (commentRsReviewDTO.getRsReviewId() == null) {
            logger.error("Review Id is null");
            throw new IllegalArgumentException("Review Id must not be null");
        }
        if (commentRsReviewDTO.getUserId() == null) {
            logger.error("User Id is null");
            throw new IllegalArgumentException("User Id must not be null");
        }
        if (commentRsReviewDTO.getRsId() == null) {
            logger.error("Resturant Id is null");
            throw new IllegalArgumentException("Restaurant Id must not be null");
        }

        RsReviewComment parentComment = Optional.ofNullable(commentRsReviewDTO.getRsParentCommentId())
                .flatMap(commentReviewRepository::findById) .orElse(null);

        RsReview rsReview = writeReviewRepository.findById(commentRsReviewDTO.getRsReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID"));
        User user = userRepository.findById(commentRsReviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(commentRsReviewDTO.getRsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));

        logger.debug("Found RsReview: {}", rsReview);
        logger.debug("Found User: {}", user);
        logger.debug("Found Restaurnat: {}", rsRestaurant);


        RsReviewComment newComment = commentRsReviewDTO.toEntity(rsReview, user, rsRestaurant, parentComment);
        newComment.setRsCommentCreatedAt(LocalDateTime.now());

        // 대댓글 깊이 설정
        if (commentRsReviewDTO.getRsParentCommentId() != null) {
            RsReviewComment rsParentCommentId = (RsReviewComment) commentReviewRepository.findById(
                    commentRsReviewDTO.getRsParentCommentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));
            newComment.setRsCommentDepth(rsParentCommentId.getRsCommentDepth() + 1);
        } else {
            newComment.setRsCommentDepth(0L);
        }
        //시발 무한츠쿠요미도 아니고;;
        return commentReviewRepository.save(newComment);
    }

    // 댓글 수정 메서드
    public RsReviewComment update(CommentRsReviewDTO commentRsReviewDTO) {
        logger.debug("Recevied RsReviewCommentDTO for update: {}",  commentRsReviewDTO);

        if (commentRsReviewDTO.getRsReviewId() == null) {
            logger.error("Review ID is null");
            throw new IllegalArgumentException("Review ID must not be null");
        }
        if (commentRsReviewDTO.getUserId() == null) {
            logger.error("User ID is null");
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (commentRsReviewDTO.getRsCommentId() == null) {
            logger.error("Comment ID is null");
            throw new IllegalArgumentException("Comment ID must not be null");
        }

        RsReviewComment
                existingComment = commentReviewRepository.findById(commentRsReviewDTO.getRsReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        RsReview rsReview = writeReviewRepository.findById(commentRsReviewDTO.getRsReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        User user = userRepository.findById(commentRsReviewDTO.getRsReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(commentRsReviewDTO.getRsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurnat ID"));

        logger.debug("Found RsReview: {}", rsReview);
        logger.debug("Found RsReview: {}", user);
        logger.debug("Found RsReview: {}", rsRestaurant);

        // 수정 내용 반영
        existingComment.setRsCommentContent(commentRsReviewDTO.getRsCommentContent());
        existingComment.setRsCommentState(commentRsReviewDTO.getRsCommentState());
        existingComment.setRsCommentUpdatedAt(commentRsReviewDTO.getRsCommentUpdatedAt());

        return commentReviewRepository.save(existingComment);

    }

    // 리뷰별 조회 메서드
    public List<CommentRsReviewDTO> findByRsReview(Long rsReviewId) {
        logger.debug("Fetch comments for review ID: {}", rsReviewId);

        RsReview rsReview = writeReviewRepository.findById(rsReviewId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID"));

        List<RsReviewComment> comments = commentReviewRepository.findByRsReview(rsReview);

        return comments.stream()
                .map(comment -> CommentRsReviewDTO.builder()
                        .rsCommentId(comment.getRsCommentId())
                        .rsCommentContent(comment.getRsCommentContent())
                        .rsCommentState(comment.getRsCommentState())
                        .rsCommentCreatedAt(comment.getRsCommentCreatedAt())
                        .rsCommentUpdatedAt(comment.getRsCommentUpdatedAt())
                        .rsCommentDeletedAt(comment.getRsCommentDeletedAt())
                        .rsCommentDepth(comment.getRsCommentDepth())
                        .rsParentCommentId(comment.getParentComment() != null ? comment.getParentComment().getRsCommentId() : null)
                        .rsReviewId(comment.getRsReview().getRsReviewId())
                        .userId(comment.getUser().getUserId())
                        .rsId(comment.getRsRestaurant().getRsId())
                        .build())
                .collect(Collectors.toList());
    }

    // 유저별 코멘트 조회 메서드
    public List<CommentRsReviewDTO> findByUser(Long userId) {
        logger.debug("Fetch comment for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        List<RsReviewComment> comments = commentReviewRepository.findByUser(user);

        return comments.stream()
                .map(comment -> CommentRsReviewDTO.builder()
                        .rsCommentId(comment.getRsCommentId())
                        .rsCommentContent(comment.getRsCommentContent())
                        .rsCommentState(comment.getRsCommentState())
                        .rsCommentCreatedAt(comment.getRsCommentCreatedAt())
                        .rsCommentUpdatedAt(comment.getRsCommentUpdatedAt())
                        .rsCommentDeletedAt(comment.getRsCommentDeletedAt())
                        .rsCommentDepth(comment.getRsCommentDepth())
                        .rsParentCommentId(comment.getParentComment() != null ? comment.getParentComment().getRsCommentId() : null)
                        .rsReviewId(comment.getRsReview().getRsReviewId())
                        .userId(comment.getUser().getUserId())
                        .rsId(comment.getRsRestaurant().getRsId())
                        .build())
                .collect(Collectors.toList());
    }

    //  댓글 삭제 처리(put) 메서드
    public RsReviewComment delete(CommentRsReviewDTO commentRsReviewDTO) {

        logger.debug("Received RsReviewCommentDTO for delete: {}", commentRsReviewDTO);

        RsReviewComment existingComment = commentReviewRepository.findById(commentRsReviewDTO.getRsCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        RsReview rsReview = writeReviewRepository.findById(commentRsReviewDTO.getRsReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID"));
        User user = userRepository.findById(commentRsReviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(commentRsReviewDTO.getRsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));

        logger.debug("Found RsReview:{}", rsReview);
        logger.debug("Found User:{}", user);
        logger.debug("Found RSRestaurnat:{}", rsRestaurant);

        // 삭제 처리
        existingComment.setRsCommentDeletedAt(LocalDateTime.now());

        return commentReviewRepository.save(existingComment);

    }

}
