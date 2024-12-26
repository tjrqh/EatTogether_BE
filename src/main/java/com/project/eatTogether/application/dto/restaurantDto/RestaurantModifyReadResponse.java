package com.project.eatTogether.application.dto.restaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class RestaurantModifyReadResponse {
  private Long id;
  private String name;
  private String description;
  private String address;
  private String contact;

}
