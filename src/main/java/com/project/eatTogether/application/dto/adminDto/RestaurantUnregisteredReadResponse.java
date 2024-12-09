package com.project.eatTogether.application.dto.adminDto;

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
public class RestaurantUnregisteredReadResponse {

  private Long rsId;
  private String rsName;
  private String rsPhone;
  private String rsPark;
  private String rsTime;

}
