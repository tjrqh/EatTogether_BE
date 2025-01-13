package com.project.eatTogether.presentation.controller;

import com.project.eatTogether.application.dto.payment.*;
import com.project.eatTogether.application.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/prepare")
    public ResponseEntity<PaymentPrepareResponse> preparePayment(@RequestBody PaymentPrepareRequestDto request) {
        PaymentPrepareResponse response = paymentService.preparePayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody PaymentVerifyRequest request) {
        // 직접 필드를 사용하는 경우
        String impUid = request.getImpUid();
        String merchantUid = request.getMerchantUid();

        // null 체크
        if (impUid == null || merchantUid == null) {
            throw new IllegalArgumentException("ImpUid와 MerchantUid는 필수값입니다.");
        }

        // 검증 로직 실행
        return ResponseEntity.ok(paymentService.verifyPayment(request));
    }


    @PostMapping("/webhook")
    public ResponseEntity<Void> handlePaymentWebhook(@RequestBody PaymentWebhookRequest webhook) {
        paymentService.processPaymentWebhook(webhook);
        return ResponseEntity.ok().build();
    }

}