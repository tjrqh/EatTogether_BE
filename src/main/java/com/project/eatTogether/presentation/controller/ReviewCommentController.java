package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import com.project.eatTogether.application.service.reviewService.CommentReviewService;
import com.project.eatTogether.domain.RsReviewComment;
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

    // 댓글 조회
    @GetMapping("/viewComment/{rsCommentId}")
    public ResponseEntity<List<CommentRsReviewDTO>> getCommentsByRsReviewId(@PathVariable Long rsCommentId) {
        logger.debug("Recevied request to git comments for review ID: {}", rsCommentId);
        List<CommentRsReviewDTO> comments = commentReviewService.findByRsReview(rsCommentId);
        logger.debug("Fetched comments: {}", comments);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

}
