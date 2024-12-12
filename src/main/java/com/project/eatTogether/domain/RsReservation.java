package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsReservationId;

    @OneToOne
    @JoinColumn(name = "rs_review_id")
    @JsonIgnore
    private RsReview rsReview;

    @ManyToOne
    @JoinColumn(name = "rs_id")
    private RsRestaurant rsRestaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="payment_id")
    private Payment payment;


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

    @Column(nullable = false)
    private LocalDateTime rsReservationCreatedAt;

    @Column
    private LocalDateTime rsReservationUpdatedAt;

    @Column
    private LocalDateTime rsReservationDeletedAt;
}
