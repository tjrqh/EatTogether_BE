package com.project.eatTogether.application.dto.restaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestaurantMenuReadResponse {

  private Long id;
  private String name;
  private String price;
  private String description;
  private String category;

}
