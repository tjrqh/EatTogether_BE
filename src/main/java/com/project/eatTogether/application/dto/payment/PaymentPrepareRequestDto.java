package com.project.eatTogether.application.dto.payment;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentPrepareRequestDto {
    private Long memberId;
    private Long rsId;
    private Integer rsReservationPartySize;
    private Integer amount;
    private String paymentMethod;
    private LocalDate rsReservationDate;
    private LocalDateTime rsReservationTime;
    private String guestName;
    private String rsReservationRequest;
}