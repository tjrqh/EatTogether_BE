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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //식당id
    public Long id;

    @Column(nullable = false)   // 식당이름
    public String name;

    @Column(nullable = false)   // 식당연락처
    public String phone;

    @Column
    public String park; // 식당 주차

    @Column
    public String time; // 식당영업시간

    @Column
    public String state;    // 식당상태(오픈,마감)

    @Column
    public Long review_count;   // 식당리뷰수

    @Column
    public Long bookmark_count; // 식당북마크수

    @Column
    public Byte avg_rate;   // 식당평균평점

    @Column
    public Long reservation_count;  // 식당예약횟수


}
