package com.project.eatTogether.domain.entity.differed;

import com.project.eatTogether.domain.enums.CuisineType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CuisineCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CuisineType type;  // KOREAN, JAPANESE, CHINESE 등

    @OneToOne(mappedBy = "cuisineCategories")
    private Restaurant restaurant;

}
