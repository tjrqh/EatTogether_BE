package com.project.eatTogether.application.dto.payment;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentVerifyRequest {
    // 결제 검증에 필요한 기본 정보
    private String impUid;        // PG사 결제 고유번호
    private String merchantUid;    // 우리 서비스의 결제 고유번호
    private ReservationInfo reservationInfo;  // 중첩 클래스 사용

    @Data
    public static class ReservationInfo {
        private Long memberId;
        private Long rsId;  // rsId를 rsRestaurantId로 변경
        private String guestName;
        private String guestPhone;
        private int rsReservationPartySize;
        private LocalDate rsReservationDate;
        String rsReservationTime;
        private String rsReservationRequest;
    }
}