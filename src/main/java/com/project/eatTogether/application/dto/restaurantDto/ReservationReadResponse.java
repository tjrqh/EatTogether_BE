package com.project.eatTogether.application.dto.restaurantDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class ReservationReadResponse {

  private Long id;
  private int guest;
  private LocalDate date;
  private String time;
  private String memberName;
  private String state;
  private String request;
  private String phone;
  private LocalDateTime deletedAt;

}
