package com.project.eatTogether.application.dto.restaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class RsRestaurantTimeUpdateRequest {
  private String rsTime;
}
