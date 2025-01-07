package com.project.eatTogether.application.dto.payment;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentVerifyRequest {
    // 결제 검증에 필요한 기본 정보
    private String impUid;        // PG사 결제 고유번호
    private String merchantUid;    // 우리 서비스의 결제 고유번호

    // 예약 생성에 필요한 정보
    private Long memberId;
    private Long rsId;
    private String guestName;
    private int partySize;
    private LocalDate reservationDate;
    private LocalDateTime reservationTime;
    private String request;
}