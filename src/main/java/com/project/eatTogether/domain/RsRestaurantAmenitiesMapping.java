package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsRestaurantAmenitiesMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsRestaurantAmenitiesMappingId;

    @ManyToOne
    @JoinColumn(name = "rs_id")
    private RsRestaurant rsRestaurant;

    @ManyToOne
    @JoinColumn(name = "rs_amenities_id")
    private RsAmenities rsAmenities;

}
