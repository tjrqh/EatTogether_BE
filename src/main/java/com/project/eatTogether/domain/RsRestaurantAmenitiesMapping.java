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

    @ManyToOne
    @JoinColumn(name = "rs_id" ,nullable = false)
    public RsRestaurant rs_restaurant;

    @ManyToOne
    @JoinColumn(name = "rs_amenities_id" ,nullable = false)
    public RsAmenities rs_amenities;

}
