package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
    private RsRestaurant rsRestaurant;

    @Column(nullable = false)
    private Integer queueNumber;

    @Column(nullable = false)
    private LocalDate queueDate;

    @Column(nullable = false)
    private LocalTime queueTime;

    @Column(nullable = false)
    private String queueState;

    @Column(nullable = false)
    @Builder.Default
    private final boolean isPrepaid = false;

    @Column(nullable = false)
    private LocalDateTime queueCreatedAt;

    @Column
    private LocalDateTime queueUpdatedAt;

    @Column
    private LocalDateTime queueDeletedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_order_id")

    private QueueOrder queueOrder;
}
