package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsLocationCtegories {

    @Id // 식당 위치 카테고리
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rs_location_id;

    @OneToOne // 식당 id
    @JoinColumn(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @Column(nullable = false) // 식당 위치 이름
    public String rs_location_name;

}
