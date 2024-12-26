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

  private Long id;
  private String name;
  private String businessNumber;
  private String address;
  private String phone;
  private String email;
  private String hours;
  private String menu;
  private String additionalInfo;
}
