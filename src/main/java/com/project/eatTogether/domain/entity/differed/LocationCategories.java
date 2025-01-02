package com.project.eatTogether.domain.entity.differed;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LocationCategories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rsLocationId;

  @Column
  private String rsLocationName;

}
