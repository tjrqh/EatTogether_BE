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

  private Long rsReservationId;
  private int reservationPartySize;
  private LocalDate rsReservationDate;
  private LocalDateTime rsReservationTime;
  private String rsReservationRequest;
  private String rsReservationState;
  



}
