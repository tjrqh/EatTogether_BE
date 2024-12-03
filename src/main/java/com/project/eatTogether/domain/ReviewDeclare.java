package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ReviewDeclare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long review_declare_id;

    @OneToOne
    @JoinColumn(name = "rs_review_id" ,nullable = false)
    public RsReview rs_review;

    @ManyToOne
    @JoinColumn(name = "user_id" ,nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rs_restaurant;

    @ManyToMany
    @JoinColumn(name = "rs_reservation_id" ,nullable = false)
    public RsReservation rs_reservation;

    @Column
    public String review_declare_content;

    @Column
    public Data review_declare_at;

    @Column
    public String review_declare_state;

}
