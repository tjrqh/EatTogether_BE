package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsRestaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rsId;

  @Column
  private String rsName;

  @Column
  private String rsPhone;

  @Column
  private String rsInfo;

  @Column
  private String rsTime;

  @Column
  private String rsState;

  @Column
  private int rsReviewCount;

  @Column
  private int rsBookmarkCount;

  @Column
  private int rsAvgRate;

  @Column
  private int rsReservationCount;

  // RsCoordinates와의 관계
  @OneToOne
  @JoinColumn(name = "rs_coordinates_id")  // Foreign Key 설정
  @JsonManagedReference
  private RsCoordinates rsCoordinates;

  // RsCuisineCategories와의 관계 (양방향 관계)
  @OneToMany(mappedBy = "rsRestaurant")  // RsCuisineCategories 클래스의 rsRestaurant 필드를 참조
  private List<RsCuisineCategories> rsCuisineCategories;

  // RsLocationCategories와의 관계
  @ManyToOne
  @JoinColumn(name = "rs_location_categories_id")
  private RsLocationCategories rsLocationCategory;

  // RsDocument와의 관계
  @OneToOne
  @JoinColumn(name = "rs_document_id")  // RsDocument를 연결하는 Foreign Key 설정
  private RsDocument rsDocument;
}
