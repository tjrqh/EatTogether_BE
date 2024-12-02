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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rs_location_id;

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @Column(nullable = false)
    public String rs_location_name;

}
