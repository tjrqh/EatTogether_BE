package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column
    private LocalDateTime paymentCreatedAt;

    @Column
    private String paymentName;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Integer paymentAmount;

    @Column(nullable = false)
    private String paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "payment")
    private RsReservation rsReservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rs_id", nullable = false)
    private RsRestaurant rsRestaurant;

    @OneToOne
    @JoinColumn(name = "queue_order_id")
    private QueueOrder queueOrder;

    @OneToOne
    @JoinColumn(name = "rs_reservation_id")
    private RsReservation reservation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

}
