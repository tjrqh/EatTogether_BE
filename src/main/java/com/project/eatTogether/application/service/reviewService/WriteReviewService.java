package com.project.eatTogether.application.service.reviewService;

import com.project.eatTogether.application.dto.ReviewReadResponse;
import com.project.eatTogether.application.dto.WriteRsReviewDTO;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.RsReview;
import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.infrastructure.repository.UserRepository;
import com.project.eatTogether.infrastructure.repository.WriteReviewRepository;
import com.project.eatTogether.infrastructure.repository.WriteRsRestaurantRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class WriteReviewService {

  private static final Logger logger = LoggerFactory.getLogger(WriteReviewService.class);

  private final WriteReviewRepository writeReviewRepository;
  private final UserRepository userRepository;
  private final WriteRsRestaurantRepository writeRsRestaurantRepository;

  public List<ReviewReadResponse> reviewList(Long id) {

    List<RsReview> RsReview = writeReviewRepository.findByRsRestaurantRsId(id);
    return RsReview.stream().map(review -> ReviewReadResponse
            .builder()
            .id(review.rsReviewId)
            .userName(review.getUser().getUserName())
            .rating(review.getRsReviewRate())
            .content(review.getRsReviewContent())
            .date(String.valueOf(review.getRsReviewCreatedAt()))
            //.images(review.getRsReview().)
            .visited(review.getRsRestaurant().getRsReservations() != null
                ? review.getRsRestaurant().getRsReservations().toString()
                : review.getRsRestaurant().getQueues().toString())
            .build())
        .collect(Collectors.toList());
  }

  public ResponseEntity<Map<String, String>> reviewDeclare(Long id) {
    String declare  = "declare";
    try{
    Optional<RsReview> rsReview = writeReviewRepository.findById(id);

    RsReview review = rsReview.orElseThrow(() ->
        new NoSuchElementException("Search Declare Not Found : " + id));
    review.setRsReviewState(declare);
    writeReviewRepository.save(review);
      return ResponseEntity.ok(Collections.singletonMap("message", "신고 성공"));


    } catch (IllegalArgumentException e) {
      System.out.println("Invalid state value: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (
        DataAccessException e) {
      System.out.println("Database error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      System.out.println("Unexpected error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Unexpected error occurred", e);
    }
  }

  //리뷰 작성 메서드
  public RsReview save(WriteRsReviewDTO writeRsReviewDTO) {
    logger.debug("Received WriteRsReviewDTO: {}", writeRsReviewDTO);

    if (writeRsReviewDTO.getRsId() == null) {
      logger.error("Restaurant ID is null");
      throw new IllegalArgumentException("Restaurant ID must not be null");
    }
    if (writeRsReviewDTO.getUserId() == null) {
      logger.error("User ID is null");
      throw new IllegalArgumentException("User ID must not be null");
    }

    RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(writeRsReviewDTO.getRsId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
    User user = userRepository.findById(writeRsReviewDTO.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

    logger.debug("Found RsRestaurant: {}", rsRestaurant);

    logger.debug("Found User: {}", user);

    RsReview newReview = writeRsReviewDTO.toEntity(rsRestaurant, user);
    newReview.setRsReviewCreatedAt(LocalDateTime.now());

    return writeReviewRepository.save(writeRsReviewDTO.toEntity(rsRestaurant, user));
  }


  // 리뷰 수정 메서드============================================================================
  public RsReview update(WriteRsReviewDTO writeRsReviewDTO) {
    logger.debug("Received WriteRsReviewDTO for update: {}", writeRsReviewDTO);

    if (writeRsReviewDTO.getRsId() == null) {
      logger.error("Restaurant ID is null");
      throw new IllegalArgumentException("Restaurant ID must not be null");
    }
    if (writeRsReviewDTO.getUserId() == null) {
      logger.error("User ID is null");
      throw new IllegalArgumentException("User ID must not be null");
    }
    if (writeRsReviewDTO.getRsReviewId() == null) {
      logger.error("Review ID is null");
      throw new IllegalArgumentException("Review ID must not be null");
    }

    RsReview existingReview = writeReviewRepository.findById(writeRsReviewDTO.getRsReviewId())
        .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(writeRsReviewDTO.getRsId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
    User user = userRepository.findById(writeRsReviewDTO.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

    logger.debug("Found RsRestaurant: {}", rsRestaurant);
    logger.debug("Found User: {}", user);

    //수정 내용 반영
    existingReview.setRsReviewRate(writeRsReviewDTO.getRsReviewRate());
    existingReview.setRsReviewState(writeRsReviewDTO.getRsReviewState());
    existingReview.setRsReviewContent(writeRsReviewDTO.getRsReviewContent());
    existingReview.setRsReviewLike(writeRsReviewDTO.getRsReviewLike());
    existingReview.setRsReviewUpdatedAt(LocalDateTime.now());

    return writeReviewRepository.save(existingReview);
  }


  // 리뷰 삭제 처리(put) 메서드============================================================================
  public RsReview delete(WriteRsReviewDTO writeRsReviewDTO) {
    logger.debug("Received WriteRsReviewDTO for delete: {}", writeRsReviewDTO);

    if (writeRsReviewDTO.getRsId() == null) {
      logger.error("Restaurant ID is null");
      throw new IllegalArgumentException("Restaurant ID must not be null");
    }
    if (writeRsReviewDTO.getUserId() == null) {
      logger.error("User ID is null");
      throw new IllegalArgumentException("User ID must not be null");
    }
    if (writeRsReviewDTO.getRsReviewId() == null) {
      logger.error("Review ID is null");
      throw new IllegalArgumentException("Review ID must not be null");
    }

    RsReview existingReview = writeReviewRepository.findById(writeRsReviewDTO.getRsReviewId())
        .orElseThrow(() -> new IllegalArgumentException("Review not found"));
    RsRestaurant rsRestaurant = writeRsRestaurantRepository.findById(writeRsReviewDTO.getRsId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant ID"));
    User user = userRepository.findById(writeRsReviewDTO.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

    logger.debug("Found Rsrestaurant: {}", rsRestaurant);
    logger.debug("Found User: {}", user);

    //수정 내용 반영
    existingReview.setRsReviewDeletedAt(LocalDateTime.now());

    return writeReviewRepository.save(existingReview);
  }
}
