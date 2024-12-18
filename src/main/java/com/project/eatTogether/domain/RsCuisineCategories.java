package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsCuisineCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rs_cuisine_category_id")  // 데이터베이스에서 사용하는 컬럼 이름을 지정
    private Long rsCuisineCategoriesId;  // 필드 이름 변경

    @Column
    private String rsCuisineCategoryName;

    // RsRestaurant와의 관계 (양방향 관계 설정)
    @ManyToOne
    @JoinColumn(name = "rs_restaurant_id")  // Foreign Key 설정
    private RsRestaurant rsRestaurant;  // RsRestaurant와의 관계

}
