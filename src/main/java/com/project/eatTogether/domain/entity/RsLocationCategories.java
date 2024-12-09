package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsLocationCategories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rsLocationId;

  @Column
  private String rsLocationName;

  @OneToOne(mappedBy = "rsLocationCategories")
  private RsRestaurant rsRestaurant;

}
