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

    @Column
    private int totalAmount;

    @Column
    private LocalDateTime orderDateTime;

    @Column
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "rs_restaurant_id")
    private RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "queueOrder")
    private List<QueueOrderItem> queueOrderItems;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
