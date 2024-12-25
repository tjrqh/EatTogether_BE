package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsAmenityId;

    @Column(nullable = false)
    private String rsAmenityName;

    @OneToMany(mappedBy = "rsAmenities")
    private List<RsRestaurantAmenitiesMapping> rsRestaurantAmenitiesMappings;

}
