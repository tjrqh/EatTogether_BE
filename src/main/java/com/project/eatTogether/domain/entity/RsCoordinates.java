package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsCoordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsCoordinatesId;

    @Column
    private float restaurantLat;

    @Column
    private float restaurantLong;

    @Column
    private String restaurantAddr;


    @OneToOne(mappedBy = "rsCoordinates")
    private RsRestaurant rsRestaurant;
}
