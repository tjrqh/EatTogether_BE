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
    public Long rs_cuisine_id;

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @Column(nullable = false)
    public String rs_cuisine_category_name;

}
