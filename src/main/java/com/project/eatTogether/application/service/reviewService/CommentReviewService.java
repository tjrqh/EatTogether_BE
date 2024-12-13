
package com.project.eatTogether.application.service.reviewService;


import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import com.project.eatTogether.application.dto.WriteRsReviewDTO;
import com.project.eatTogether.domain.RsRestaurant;
import com.project.eatTogether.domain.RsReview;
import com.project.eatTogether.domain.RsReviewComment;
import com.project.eatTogether.domain.User;
import com.project.eatTogether.domain.repository.CommentReviewRepository;
import com.project.eatTogether.domain.repository.UserRepository;
import com.project.eatTogether.domain.repository.WriteReviewRepository;
import com.project.eatTogether.domain.repository.WriteRsRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class    CommentReviewService {

    private static final Logger logger = LoggerFactory.getLogger(
            CommentReviewService.class);

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

        RsReview rsReview = writeReviewRepository.findById(commentRsReviewDTO.getRsReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID"));
        User user = userRepository.findById(commentRsReviewDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(commentRsReviewDTO.getRsId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));

        logger.debug("Found RsReview: {}", rsReview);
        logger.debug("Found User: {}", user);
        logger.debug("Found Restaurnat: {}", rsRestaurant);

        RsReviewComment newComment = commentRsReviewDTO.toEntity(rsReview, user, rsRestaurant);
        newComment.setRsCommentCreatedAt(LocalDateTime.now());

        // 대댓글 깊이 설정
        if (commentRsReviewDTO.getRsParentCommentId() != null) {
            CommentRsReviewDTO rsParentCommentId = commentReviewRepository.findById(
                    commentRsReviewDTO.getRsParentCommentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));
            newComment.setRsCommentDepth(rsParentCommentId.getRsCommentDepth() + 1);
        } else {
            newComment.setRsCommentDepth(0L);
        }
        //시발 무한츠쿠요미도 아니고;;
        return commentReviewRepository.save(newComment)

    }

}
