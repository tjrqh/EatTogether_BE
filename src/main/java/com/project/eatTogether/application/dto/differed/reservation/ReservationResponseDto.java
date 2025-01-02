package com.project.eatTogether.application.dto.differed.reservation;//package com.project.eattogether.dto.reservation;
//
//import com.project.eattogether.domain.RsReservation;
//import lombok.Builder;
//import lombok.Data;
//import lombok.extern.log4j.Log4j2;
//
//import java.time.LocalDateTime;
//
//@Log4j2
//@Data
//@Builder
//public class ReservationResponseDto {
//    Long memberId;
//    String memberName;
//    String email;
//    String memberPhone;
//
//    Long rsId;
//    String rsName;
//    String guestName;
//    Long rsReservationId;
//    int rsReservationPartySize;
//    String rsReservationRequest;
//    String rsReservationState;
//    LocalDateTime rsReservationTime;
//    LocalDateTime rsReservationCreatedAt;
//
//    //예약금 있을 경우 예약금 관련된 컬럼 추가
//
//
//    public static ReservationResponseDto entityToDto(RsReservation rsReservation) {
//        return ReservationResponseDto.builder()
//                .rsReservationId(rsReservation.getRsReservationId())
//                .memberId(rsReservation.getMember().getMemberId())
//                .memberName(rsReservation.getMember().getMemberName())
//                .email(rsReservation.getMember().getEmail())
//                .guestName(rsReservation.getGuestName())
//                .memberPhone(rsReservation.getMember().getMemberPhone())
//                .rsId(rsReservation.getRsRestaurant().getRsId())
//                .rsName(rsReservation.getRsRestaurant().getRsName())
//                .rsReservationPartySize(rsReservation.getRsReservationPartySize())
//                .rsReservationState(rsReservation.getRsReservationState())
//                .rsReservationRequest(rsReservation.getRsReservationRequest())
//                .rsReservationTime(rsReservation.getRsReservationTime())
//                .rsReservationCreatedAt(rsReservation.getRsReservationCreatedAt())
//                .build();
//    }
//
//}
