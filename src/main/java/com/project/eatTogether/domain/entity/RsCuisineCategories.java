package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.CuisineType;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CuisineType type;  // KOREAN, JAPANESE, CHINESE 등

    @OneToOne(mappedBy = "rsCuisineCategories")
    private RsRestaurant rsRestaurant;

}
