package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.entity.differed.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class RsReservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsReservationId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_review_id")
    @JsonIgnore
    private RsReview rsReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id")
    @JsonIgnore
    private RsRestaurant rsRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payment_id")
    private Payment payment;

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private String guestPhone;

    @Column(nullable = false)
    private int rsReservationPartySize;

    @Column(nullable = false)
    private LocalDate rsReservationDate;

    @Column(nullable = false)
    private LocalDateTime rsReservationTime;

    @Column
    private String rsReservationRequest;

    @Column(nullable = false)
    private String rsReservationState;

    @Builder
    public RsReservation(RsRestaurant rsRestaurant, Member member, String guestName, String guestPhone,
                       int rsReservationPartySize, LocalDate rsReservationDate,
                       LocalDateTime rsReservationTime, String rsReservationRequest,
                       String rsReservationState) {
        this.rsRestaurant = rsRestaurant;
        this.member = member;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.rsReservationPartySize = rsReservationPartySize;
        this.rsReservationDate = rsReservationDate;
        this.rsReservationTime = rsReservationTime;
        this.rsReservationRequest = rsReservationRequest;
        this.rsReservationState = rsReservationState;
    }

    public static RsReservation createReservation(RsRestaurant rsRestaurant, Member member, String guestName,
                                                String guestPhone, int rsReservationPartySize,
                                                LocalDate rsReservationDate,
                                                LocalDateTime rsReservationTime,
                                                String rsReservationState, String rsReservationRequest) {
        return RsReservation.builder()
                .rsRestaurant(rsRestaurant)
                .member(member)
                .guestName(guestName)
                .guestPhone(guestPhone)// 회원 이름을 게스트 이름으로 사용
                .rsReservationPartySize(rsReservationPartySize)
                .rsReservationDate(rsReservationDate)
                .rsReservationTime(rsReservationTime)
                .rsReservationState(rsReservationState)
                .rsReservationState("PENDING")
                .rsReservationRequest(rsReservationRequest)
                .build();
    }

}
