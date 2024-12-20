package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import com.project.eatTogether.application.service.reviewService.CommentReviewService;
import com.project.eatTogether.domain.entity.RsReviewComment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewCommentController {

    @Autowired
    private final CommentReviewService commentReviewService;

    private static final Logger logger = LoggerFactory.getLogger(
            ReviewCommentController.class);

    // 댓글 작성
    @PostMapping("/createComment")
    public ResponseEntity<RsReviewComment>
    createComment(@RequestBody CommentRsReviewDTO commentRsReviewDTO) {
        logger.debug("Recevied request to create comment: {}", commentRsReviewDTO);
        RsReviewComment createdComment = commentReviewService.save(commentRsReviewDTO);
        logger.debug("Create commnet: {}", createdComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    // 댓글 수정
    @PutMapping("/updateCommemt/{rsCommentId}")
    public ResponseEntity<RsReviewComment>
    updateComment(@PathVariable Long rsCommentId, @RequestBody CommentRsReviewDTO commentRsReviewDTO) {
        logger.debug("Recevied request to update comment with ID: {}", rsCommentId);
        commentRsReviewDTO.setRsCommentId(rsCommentId);
        RsReviewComment updatedComment = commentReviewService.update(commentRsReviewDTO);
        logger.debug("Updated comment: {}", updatedComment);
        return ResponseEntity.status(HttpStatus.OK).body(updatedComment);
    }

    // 댓글 삭제
    @PutMapping("/delelteComment/{rsCommentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long rsCommentId,
                                              @RequestBody CommentRsReviewDTO commentRsReviewDTO) {
        logger.debug("Received request to delete with ID: {}", rsCommentId);
        commentRsReviewDTO.setRsCommentId(rsCommentId);
        commentReviewService.delete(commentRsReviewDTO);
        logger.debug("Deleted comment with ID: {}", rsCommentId);
        return ResponseEntity.noContent().build();
    }

    // 리뷰에 달린 전체 댓글 조회
    @GetMapping("/viewCommentByReview/{rsReviewId}")
    public ResponseEntity<List<CommentRsReviewDTO>> getCommentsByRsReviewId(@PathVariable Long rsReviewId) {
        logger.debug("Recevied request to get comments for review ID: {}", rsReviewId);
        List<CommentRsReviewDTO> comments = commentReviewService.findByRsReview(rsReviewId);
        logger.debug("Fetched comments: {}", comments);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    // 유저가 쓴 댓글 조회
    @GetMapping("/viewCommentByUser/{userId}")
    public ResponseEntity<List<CommentRsReviewDTO>> getCommentByUserId(@PathVariable Long userId) {
        logger.debug("Recevied request to get comment for user ID: {}", userId);
        List<CommentRsReviewDTO> comments = commentReviewService.findByUser(userId);
        logger.debug("Fetch comments: {}", comments);
        return  ResponseEntity.status(HttpStatus.OK).body(comments);
    }

}
