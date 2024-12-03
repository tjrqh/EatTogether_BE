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

    @Id // 식당종류카테고리id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rs_cuisine_id;

    @OneToOne // 식당 id
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rs_restaurant;

    @Column(nullable = false)   // 식당종류이름
    public String rs_cuisine_category_name;

}
