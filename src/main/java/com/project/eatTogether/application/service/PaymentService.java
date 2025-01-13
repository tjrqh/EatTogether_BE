package com.project.eatTogether.application.service;

import com.project.eatTogether.application.dto.payment.*;
import com.project.eatTogether.domain.entity.Payment;
import com.project.eatTogether.domain.entity.PaymentType;
import com.project.eatTogether.domain.entity.RsReservation;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.infrastructure.PaymentRepository;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import com.project.eatTogether.infrastructure.differed.MemberRepository;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final RsRestaurantRepository restaurantRepository;
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final RestaurantReservationRepository reservationRepository;

    public PaymentPrepareResponse preparePayment(PaymentPrepareRequestDto request) {
        RsRestaurant restaurant = restaurantRepository.findByRsId(request.getRsId())
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));

        // 결제 정보 생성
        Payment payment = Payment.builder()
                .paymentAmount(request.getAmount())
                .paymentStatus("PENDING")
                .paymentType(PaymentType.RESERVATION)
                .paymentMethod(request.getPaymentMethod())
                .member(member)
                .rsRestaurant(restaurant)
                .build();

        payment = paymentRepository.save(payment);

        return PaymentPrepareResponse.builder()
                .merchantUid(payment.getPaymentId().toString())
                .amount(payment.getPaymentAmount())
                .build();
    }

    @Transactional
    public PaymentVerifyResponse verifyPayment(PaymentVerifyRequest request) {

        Member member = memberRepository.findById(request.getReservationInfo().getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Payment payment = paymentRepository.findById(Long.parseLong(request.getMerchantUid()))
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        if (verifyWithPortone(request.getImpUid(), payment.getPaymentAmount())) {
            // 결제 상태 업데이트
            payment.setPaymentStatus("COMPLETED");

            LocalDateTime reservationTime = LocalTime.parse(request.getReservationInfo().getRsReservationTime())
                    .atDate(request.getReservationInfo().getRsReservationDate());

            // 예약 생성
            RsReservation reservation = RsReservation.createReservation(
                    payment.getRsRestaurant(),
                    payment.getMember(),
                    Optional.ofNullable(request.getReservationInfo().getGuestName())
                            .orElse(payment.getMember().getName()),  // member의 이름을 기본값으로 사용
                    Optional.ofNullable(request.getReservationInfo().getGuestPhone())
                            .orElse(payment.getMember().getPhone()),
                    request.getReservationInfo().getRsReservationPartySize(),
                    request.getReservationInfo().getRsReservationDate(),
                    reservationTime,
                    "CONFIRMED",
                    request.getReservationInfo().getRsReservationRequest()
            );

            // 예약과 결제 연결
            reservation.setPayment(payment);
            payment.setReservation(reservation);

            // 저장
            paymentRepository.save(payment);
            reservationRepository.save(reservation);

            return new PaymentVerifyResponse(true, "Payment and reservation completed");
        }

        return new PaymentVerifyResponse(false, "Payment verification failed");
    }

    private boolean verifyWithPortone(String impUid, Integer amount) {
        // Implement Portone API verification logic
        return true; // Placeholder
    }

    public void processPaymentWebhook(PaymentWebhookRequest webhook) {
        Payment payment = paymentRepository.findById(Long.parseLong(webhook.getMerchant_uid()))
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));

        switch (webhook.getStatus()) {
            case "paid":
                payment.setPaymentStatus("COMPLETED");
                break;
            case "failed":
                payment.setPaymentStatus("FAILED");
                break;
            case "cancelled":
                payment.setPaymentStatus("CANCELLED");
                break;
        }

        paymentRepository.save(payment);
    }
}
