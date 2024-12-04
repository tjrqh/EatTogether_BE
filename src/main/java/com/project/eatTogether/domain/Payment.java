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

    @Column
    private String paymentMethod;

    @Column
    private String paymentAmount;

    @Column
    private String paymentSize;

    @Column
    private String paymentType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "payment")
    private RsReservation rsReservation;

    @ManyToOne
    @JoinColumn(name="rs_restaurant_id")
    private RsRestaurant rsRestaurant;

    @OneToOne(mappedBy = "payment")
    private QueueOrder queueOrder;
}
