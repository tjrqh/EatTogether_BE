package com.project.eatTogether.application.dto.restaurantDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class QueueOrderReadResponse {

  private Long queueOrderId;
  private int totalAmount;


}
