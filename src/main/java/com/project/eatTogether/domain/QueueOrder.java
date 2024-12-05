package com.project.eatTogether.domain;

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

    @OneToOne
    @JoinColumn(name = "queue_id", nullable = false)
    private Queue queue;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
    private RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "queueOrder", cascade = CascadeType.ALL)
    private List<QueueOrderItem> queueOrderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(mappedBy = "queueOrder")
    @JoinColumn(name = "payment_id")
    private Payment payment;


}
