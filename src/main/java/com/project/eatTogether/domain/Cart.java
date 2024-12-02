package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToMany
    @Column(name = "user_id", nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id", nullable = false)
    public Restaurant restaurant;

    @Column
    private Long amount;

    @Column // 등록일
    public LocalDate created_at;

    @Column // 수정일
    public LocalDate updated_at;

    @Column // 삭제일
    public LocalDate deleted_at;

}
