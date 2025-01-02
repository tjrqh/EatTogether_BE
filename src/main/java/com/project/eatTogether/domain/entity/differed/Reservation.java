package com.project.eatTogether.domain.entity.differed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReservationId;

    @OneToOne
    @JoinColumn(name = "_review_id")
    @JsonIgnore
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_id")
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;


//    @OneToOne
//    @JoinColumn(name="payment_id")
//    private Payment payment;

    @Column(nullable = false)
    private String guestName;

    @Column(nullable = false)
    private int ReservationPartySize;

    @Column(nullable = false)
    private LocalDate ReservationDate;

    @Column(nullable = false)
    private LocalDateTime ReservationTime;

    @Column
    private String ReservationRequest;

    @Column(nullable = false)
    private String ReservationState;

    @Column(nullable = false)
    private LocalDateTime ReservationCreatedAt;

    @Column
    private LocalDateTime ReservationUpdatedAt;

    @Column
    private LocalDateTime ReservationDeletedAt;

    @Builder
    public Reservation(Restaurant restaurant, Member member, String guestName,
                       int ReservationPartySize, LocalDate ReservationDate,
                       LocalDateTime ReservationTime, String ReservationRequest,
                       String ReservationState) {
        this.restaurant = restaurant;
        this.member = member;
        this.guestName = guestName;
        this.ReservationPartySize = ReservationPartySize;
        this.ReservationDate = ReservationDate;
        this.ReservationTime = ReservationTime;
        this.ReservationRequest = ReservationRequest;
        this.ReservationState = ReservationState;
        this.ReservationCreatedAt = LocalDateTime.now();
    }

    public static Reservation createReservation(Restaurant restaurant, Member member, String guestName,
                                                int ReservationPartySize,
                                                LocalDate ReservationDate,
                                                LocalDateTime ReservationTime,
                                                String ReservationRequest) {
        return Reservation.builder()
                .restaurant(restaurant)
                .member(member)
                .guestName(guestName)  // 회원 이름을 게스트 이름으로 사용
                .ReservationPartySize(ReservationPartySize)
                .ReservationDate(ReservationDate)
                .ReservationTime(ReservationTime)
                .ReservationRequest(ReservationRequest)
                .ReservationState("PENDING")
                .build();
    }

}
