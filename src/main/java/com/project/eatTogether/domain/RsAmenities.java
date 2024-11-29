package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsRestaurantAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String rs_amenities_id;

    @Column(nullable = false)
    public String rs_amenities_name;

}
