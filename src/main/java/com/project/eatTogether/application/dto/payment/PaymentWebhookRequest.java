package com.project.eatTogether.application.dto.payment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentWebhookRequest {
    private String imp_uid;          // 포트원 결제 고유번호
    private String merchant_uid;      // 주문번호
    private String status;           // 결제상태 (ready, paid, failed, cancelled)
    private Integer amount;          // 결제금액
    private String payment_method;    // 결제수단
    private LocalDateTime paid_at;    // 결제시각
    private String failure_reason;    // 실패사유
    private String cancel_reason;     // 취소사유
}