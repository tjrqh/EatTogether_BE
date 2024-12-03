package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsRestaurant {

    @Id
    @OneToOne(mappedBy = "rs_restaurant", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "rs_restaurant", cascade = CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //식당id
    public Long rs_id;

    @Column(nullable = false)   // 식당이름
    public String rs_name;

    @Column(nullable = false)   // 식당연락처
    public String rs_phone;

    @Column
    public String rs_park; // 식당 주차

    @Column
    public String rs_time; // 식당영업시간

    @Column
    public String rs_state;    // 식당상태(오픈,마감)

    @Column
    public Long rs_review_count;   // 식당리뷰수

    @Column
    public Long rs_bookmark_count; // 식당북마크수

    @Column
    public Byte rs_avg_rate;   // 식당평균평점

    @Column
    public Long rs_reservation_count;  // 식당예약횟수


}
