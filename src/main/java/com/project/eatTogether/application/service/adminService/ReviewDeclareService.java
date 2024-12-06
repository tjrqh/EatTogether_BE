package com.project.eatTogether.application.service.adminService;

import com.project.eatTogether.application.dto.adminDto.ReviewDeclareStateSearchReadResponse;
import com.project.eatTogether.domain.ReviewDeclare;
import com.project.eatTogether.infrastructure.adminInfra.ReviewDeclareRepository;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewDeclareService {

  private final ReviewDeclareRepository reviewDeclareRepository;

  //신고된 리뷰 보기
  public List<ReviewDeclareStateSearchReadResponse> checkReviewDeclare(String declare) {
    try {
      return reviewDeclareRepository.findByReviewDeclareState(declare)
          .stream()
          .map(review -> ReviewDeclareStateSearchReadResponse
              .builder()
              .reviewDeclareId(review.getReviewDeclareId())
              .reviewDeclareContent(review.getReviewDeclareContent())
              .reviewDeclareState(review.getReviewDeclareState())
              .reviewDeclareCreatedAt(review.getReviewDeclareCreatedAt())
              .build())
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("checkReviewDeclare error : ", e);
      throw new RuntimeException(
          "Unexpected error occurred while processing review declare states : ", e);
    }
  }

  //신고된 리뷰 경고 및 삭제 처리
  public void updateReviewDeclareState(Long id,String state) {
    Optional<ReviewDeclare> declare = reviewDeclareRepository.findById(id);

    ReviewDeclare reviewDeclare = declare.orElseThrow(() ->
        new NoSuchElementException("Search Declare Not Found : " + id));

    reviewDeclare.setReviewDeclareState(state);
    reviewDeclareRepository.save(reviewDeclare);
  }

}
