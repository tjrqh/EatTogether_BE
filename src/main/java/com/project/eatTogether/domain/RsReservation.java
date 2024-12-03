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
    @OneToMany(mappedBy = "rs_reservation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rs_reservation_id;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rs_restaurant;

    @Column(nullable = false)
    public Long rs_reservation_party_size; // 인원수

    @Column(nullable = false)
    public LocalDate rs_reservation_date;  // 예약일

    @Column(nullable = false)
    public LocalDateTime rs_reservation_time;  // 에약시간

    @Column
    public String rs_reservation_request;  // 요청사항

    @Column(nullable = false)
    public String rs_reservation_state;    // 예약상태

    @Column
    public LocalDate rs_reservation_created_at;    // 등록일

    @Column
    public LocalDate rs_reservation_updated_at;    // 수정일

    @Column
    public LocalDate rs_reservation_deleted_at;    // 삭제일

}
