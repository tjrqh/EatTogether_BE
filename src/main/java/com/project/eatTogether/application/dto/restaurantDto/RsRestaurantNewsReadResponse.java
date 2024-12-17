package com.project.eatTogether.application.dto.restaurantDto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RsRestaurantNewsReadResponse {
  private Long rsNewsId;
  private Long rsId;
  private String rsNewsContent;
  private LocalDateTime rsNewsPublishedCreatedAt;
  private LocalDateTime rsNewsUpdatedAt;
  private List<MultipartFile> rsNewsImages;
  }
