package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.ReviewReadResponse;
import com.project.eatTogether.application.dto.WriteRsReviewDTO;
import com.project.eatTogether.application.service.reviewService.WriteReviewService;
import com.project.eatTogether.domain.entity.RsReview;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WriteReviewController {

    @Autowired
    private WriteReviewService writeReviewService;

    @GetMapping("/review")
    public List<ReviewReadResponse> getReviewByRsId(){
        Long id = 1L;
        return writeReviewService.reviewList(id);
    }

    // POST : 리뷰 작성
    @PostMapping("/createReview")
    public ResponseEntity<RsReview> addReivew(@RequestBody WriteRsReviewDTO writeRsReviewDTO) {
        RsReview saveRsReview = writeReviewService.save(writeRsReviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRsReview);
    }

    //PUT : 리뷰 수정
    @PutMapping("/updateReview")
    public ResponseEntity<RsReview> updateReview(@RequestBody WriteRsReviewDTO writeRsReviewDTO) {
        RsReview updatedRsReview = writeReviewService.update(writeRsReviewDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRsReview);
    }

    //PUT : 리뷰 삭제 처리
    @PutMapping("/deleteReview")
    public ResponseEntity<RsReview> deleteReview(@RequestBody WriteRsReviewDTO writeRsReviewDTO) {
        RsReview deletedRsReview = writeReviewService.delete(writeRsReviewDTO);
        return ResponseEntity.status(HttpStatus.OK).body(deletedRsReview);
    }
}
