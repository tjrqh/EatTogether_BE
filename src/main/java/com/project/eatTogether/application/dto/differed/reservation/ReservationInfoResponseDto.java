package com.project.eatTogether.application.dto.differed.reservation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationInfoResponseDto {

    private Long rsId;
    private String rsName;
    private String guestName;
    private boolean rsDepositRequired;
    private int rsDepositAmount;

}
