package com.project.eatTogether.application.service.adminService;

import com.project.eatTogether.application.dto.adminDto.ReviewDeclareReadResponse;
import com.project.eatTogether.application.service.userService.UserService;
import com.project.eatTogether.domain.entity.ReviewDeclare;
import com.project.eatTogether.domain.entity.RsReview;
import com.project.eatTogether.infrastructure.RsReviewsRepository;
import com.project.eatTogether.infrastructure.adminInfra.ReviewDeclareRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewDeclareService {

  private final ReviewDeclareRepository reviewDeclareRepository;
  private final RsReviewsRepository rsReviewsRepository;
  private final UserService userService;

  public List<ReviewDeclareReadResponse> checkReviewDeclare(Long id) {

    List<ReviewDeclare> reviewDeclare = reviewDeclareRepository.findAllByReviewState();
    return reviewDeclare.stream().map(review -> ReviewDeclareReadResponse
        .builder()
        .id(review.getReviewDeclareId())
        .userName(review.getRsReview().user.getUserName())
        .rating(review.getRsReview().rsReviewRate)
        .date(String.valueOf(review.getRsReview().getRsReviewCreatedAt()))
        .content(review.getRsReview().getRsReviewContent())
        //.images(review.getRsReview().)
        .visited(review.getRsReview().getRsRestaurant().getRsReservations().toString())
        .build())
        .collect(Collectors.toList());
  }

  //신고된 리뷰 경고 및 삭제 처리
  @Transactional
  public void updateReviewDeclareState(Long id,String state) {
    Optional<ReviewDeclare> declare = reviewDeclareRepository.findById(id);

    ReviewDeclare reviewDeclare = declare.orElseThrow(() ->
        new NoSuchElementException("Search Declare Not Found : " + id));

    try {
            reviewDeclare.setReviewDeclareState(state);
            if(Objects.equals(state, "삭제")){
              // 신고된 리뷰 상태 변경
              Optional<RsReview> rsReview = rsReviewsRepository.findById(reviewDeclare.getRsReview().rsReviewId);
              RsReview review = rsReview.orElseThrow(() ->new NoSuchElementException("Search Review Not Found : " + reviewDeclare.getRsReview().rsReviewId));
              review.setRsReviewState(state);
              // 신고된 리뷰 유저 경고 1회 부여
              ResponseEntity<String> user = userService.userStateSanctionService(reviewDeclare.getRsReview().user.getUserId());
              if (!user.equals(ResponseEntity.ok("Ok"))){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Not find User");
              }
            }
            reviewDeclareRepository.save(reviewDeclare);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid state value: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
        } catch (DataAccessException e) {
            System.out.println("Database error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", e);
        }
  }

}
