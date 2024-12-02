package com.project.eatTogether.domain;

import jakarta.persistence.*;
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
    public Long id;

    @ManyToMany
    @Column(name = "user_id", nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id", nullable = false)
    public Restaurant restaurant;

    @Column
    public Long amount;

    @Column
    public LocalDateTime dateTime;

    @Column
    public String status;
}
