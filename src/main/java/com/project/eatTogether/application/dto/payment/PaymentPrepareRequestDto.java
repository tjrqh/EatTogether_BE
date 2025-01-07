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
    private Integer partySize;
    private Integer amount;
    private String paymentMethod;
    private LocalDate reservationDate;
    private LocalDateTime reservationTime;
    private String guestName;
    private String request;
}