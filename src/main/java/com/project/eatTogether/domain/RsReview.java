package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsReview {

    @Id //식당 리뷰 id
    @OneToOne(mappedBy = "rs_review", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "rs_review", cascade = CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rs_review_id;

    @ManyToOne // 유저 id
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @ManyToOne // 식당 id
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rs_restaurant;

    @OneToOne // 식당 예약 id
    @JoinColumn(name = "rs_reservation_id" ,nullable = false)
    public RsReservation rs_reservation;

    @Column(nullable = false)
    public String rs_review_content;

    @Column
    public Byte rs_review_rate;

    @Column(nullable = false)
    public Date rs_created_at;

    @Column
    public String rs_review_state;

    @Column
    public long rs_review_like;

}
