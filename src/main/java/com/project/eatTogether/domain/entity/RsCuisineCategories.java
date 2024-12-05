package com.project.eatTogether.domain.entity;

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
    private Long rsCuisineCategoryId;

    @Column(nullable = false)
    private String rsCuisineCategoryName;

    @OneToOne(mappedBy = "rsCuisineCategories")
    private RsRestaurant rsRestaurant;

}
