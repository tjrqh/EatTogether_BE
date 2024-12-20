package com.project.eatTogether.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @ManyToOne
  @JoinColumn(name = "rs_restaurant_id")  // RsRestaurant와 연결되는 외래키
  private RsRestaurant rsRestaurant;
}
