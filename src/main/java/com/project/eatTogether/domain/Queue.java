package com.project.eatTogether.domain;

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
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    public Long id;

    @ManyToMany
    @Column(name = "user_id", nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id", nullable = false)
    public RsRestaurant rsRestaurant;

    @Column
    public Byte number;

    @Column
    public LocalDate date;

    @Column
    public LocalDateTime time;

    @Column
    public String state;

    @Column
    public LocalDateTime created_at;

    @Column
    public LocalDateTime updated_at;

    @Column
    public LocalDateTime deleted_at;

}
