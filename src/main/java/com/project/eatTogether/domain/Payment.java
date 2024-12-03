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
    private Long id;

    @ManyToMany
    @Column(name = "user_id", nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id", nullable = false)
    public RsRestaurant rsRestaurant;

    @ManyToMany
    @Column(name = "id")
    public RsReservation rsReservation;

    @ManyToMany
    @Column(name = "id")
    public QueueOrder queueOrder;

    @Column
    private LocalDateTime created_at;

    @Column
    private String name;

    @Column
    private String method;

    @Column
    private Long amount;

    @Column
    private  String state;

    @Column
    private String type;

}
