package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    @Column(nullable = false)
    private int queueNumber;

    @Column(nullable = false)
    private LocalDate queueDate;

    @Column(nullable = false)
    private LocalTime queueTime;

    @Column(nullable = false)
    private String queueState;

    @Column(nullable = false)
    private LocalDateTime queueCreatedAt;

    @Column
    private LocalDateTime queueUpdatedAt;

    @Column
    private LocalDateTime queueDeletedAt;


    @ManyToOne
    @JoinColumn(name = "rs_restaurant_id")
    private RsRestaurant rsRestaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
