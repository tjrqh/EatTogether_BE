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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 편의시설코드id
    public String id;

    @Column(nullable = false)   // 편의시설이름
    public String name;

}
