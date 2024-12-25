package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class QueueOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueOrderId;

    @Column(nullable = false)
    private int totalAmount;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @Column
    private String orderStatus;

    @Column
    private String queueOrderRequestMemo;

    @OneToOne(mappedBy = "queueOrder",fetch = FetchType.LAZY)
    private Queue queue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
    private RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "queueOrder", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<QueueOrderItem> queueOrderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "queueOrder",fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;


}
