package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsAmenities {

    @Id
    @OneToMany(mappedBy = "rs_amenities")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 편의시설코드id
    public String rs_amenity_id;

    @Column(nullable = false)   // 편의시설이름
    public String rs_amenity_name;

}
