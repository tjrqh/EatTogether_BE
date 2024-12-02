package com.project.eatTogether.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsRestaurantAmenitiesMapping {

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant;

    @ManyToMany
    @Column(name = "id" ,nullable = false)
    public RsAmenities rsAmenities;

}
