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
public class RsReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToMany
    @Column(name = "user_id" ,nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @Column(nullable = false)
    public Long party_size; // 인원수

    @Column(nullable = false)
    public LocalDate date;  // 예약일

    @Column(nullable = false)
    public LocalDateTime time;  // 에약시간

    @Column
    public String request;  // 요청사항

    @Column(nullable = false)
    public String state;    // 예약상태

    @Column
    public LocalDate created_at;    // 등록일

    @Column
    public LocalDate updated_at;    // 수정일

    @Column
    public LocalDate deleted_at;    // 삭제일

}
