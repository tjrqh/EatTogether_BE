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

    @ManyToMany
    @Column(name = "rs_review_id" ,nullable = false)
    public RsReview rsReview;

    @ManyToMany
    @Column(name = "user_id" ,nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @ManyToMany
    @Column(name = "rs_reservation_id" ,nullable = false)
    public RsReservation rsReservation;

    @Column
    public String review_declare_content;

    @Column
    public Data review_declare_at;

    @Column
    public String review_declare_state;

}
