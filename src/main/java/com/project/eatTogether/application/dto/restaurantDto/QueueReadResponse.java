package com.project.eatTogether.application.dto.restaurantDto;

import java.time.LocalDate;
import java.time.LocalTime;
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
public class QueueReadResponse {
  private Long queueId;
  private int queueNumber;
  private LocalDate queueDate;
  private LocalTime queueTime;
  private String queueState;
}
