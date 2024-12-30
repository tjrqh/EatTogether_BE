package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.application.dto.restaurantDto.RsRestaurantNewsReadResponse;
import com.project.eatTogether.domain.entity.RsNews;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantNewsRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantNewsService {

  private final RestaurantNewsRepository restaurantNewsRepository;
  private final RsRestaurantRepository restaurantRepository;

  public List<RsRestaurantNewsReadResponse> rsRestaurantNewsReadByRestaurantId(Long rsId) {
    return restaurantNewsRepository.findByRestaurantRsId(rsId)
            .stream()
            .map(news -> RsRestaurantNewsReadResponse
                    .builder()
                    .rsNewsId(news.getRsNewsId())
                    .rsNewsContent(news.getRsNewsContent())
                    .createdAt(news.getCreatedAt())         // 변경
                    .modifiedAt(news.getModifiedAt())       // 변경
                    .build())
            .collect(Collectors.toList());
  }


  public List<RsRestaurantNewsReadResponse> rsRestaurantNewsModifyPageReadByRsNewsId(Long rsNewsId) {
    return restaurantNewsRepository.findById(rsNewsId)
            .stream().map(rsNews -> RsRestaurantNewsReadResponse
                    .builder()
                    .createdAt(rsNews.getCreatedAt())       // 변경
                    .rsNewsContent(rsNews.getRsNewsContent())
                    .build())
            .collect(Collectors.toList());
  }

  @Transactional
  public ResponseEntity<String> rsRestaurantNewsCreate(Long rsId, String newsContent, List<MultipartFile> rsNewsImages) {
    try {
      RsRestaurant rsRestaurant = restaurantRepository.findById(rsId)
              .orElseThrow(() -> new RuntimeException("RsRestaurant not found"));
      RsNews rsNews = new RsNews();
      rsNews.setRsRestaurant(rsRestaurant);
      rsNews.setRsNewsContent(newsContent);
      // createdAt은 BaseEntity에서 자동으로 설정됨
      restaurantNewsRepository.save(rsNews);

      return ResponseEntity.ok("save Success");
    } catch (IllegalArgumentException e) {
      log.error("Invalid state value: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (DataAccessException e) {
      log.error("Database error: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      log.error("Unexpected error: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
              "Unexpected error occurred", e);
    }
  }

  public ResponseEntity<String> rsRestaurantNewsUpdate(Long rsId, Long rsNewsId, String rsNewsContent, List<MultipartFile> rsNewsImages) {
    try {
      RsNews news = restaurantNewsRepository.findById(rsNewsId)
              .orElseThrow(() -> new RuntimeException("RsRestaurant not found"));
      news.setRsNewsContent(rsNewsContent);
      // modifiedAt은 BaseEntity에서 자동으로 설정됨
      restaurantNewsRepository.save(news);
      return ResponseEntity.ok("update Success");
    } catch (IllegalArgumentException e) {
      log.error("Invalid state value: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (DataAccessException e) {
      log.error("Database error: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      log.error("Unexpected error: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
              "Unexpected error occurred", e);
    }
  }

  public ResponseEntity<String> rsRestaurantNewsDelete(Long id) {
    try {
      RsNews news = restaurantNewsRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("NewsId not found"));
      news.delete();  // BaseEntity의 delete() 메서드 사용
      restaurantNewsRepository.save(news);
      return ResponseEntity.ok("delete Success");
    } catch (IllegalArgumentException e) {
      log.error("Invalid state value: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (DataAccessException e) {
      log.error("Database error: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      log.error("Unexpected error: {}", e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
              "Unexpected error occurred", e);
    }
  }
}
