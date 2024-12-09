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
public class RsRestaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long rsId;

  @Column(nullable = false)
  private String rsName;

  @Column(nullable = false)
  private String rsPhone;

  @Column
  private String rsPark;

  @Column
  private String rsTime;

  @Column
  private String rsState;

  @Column
  private String rsReviewCount;

  @Column
  private int rsBookmarkCount;

  @Column
  private int rsAvgRate;

  @Column
  private int rsReservationCount;

  @Column
  private boolean rsDepositRequired = false;  // 예약금 필수 여부, 기본값 false

  @Column
  private Integer rsDepositAmount = 0;  // 예약금 금액, 기본값 0


  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsReview> rsReviews;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsReservation> rsReservations;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<Queue> queues;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<Payment> payments;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<Bookmark> bookmarks;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsTable> rsTables;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<Cart> carts;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<QueueOrder> queueOrders;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsRestaurantAmenitiesMapping> rsRestaurantAmenitiesMappings;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsMenus> rsMenus;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsNews> rsNews;

  @OneToOne
  @JoinColumn(name = "rs_coordinates_id")
  private RsCoordinates rsCoordinates;

  @OneToOne
  @JoinColumn(name = "rs_cuisine_categories_id")
  private RsCuisineCategories rsCuisineCategories;

  @OneToOne
  @JoinColumn(name = "rs_document_id")
  private RsDocument rsDocument;

  @OneToOne
  @JoinColumn(name = "rs_location_categories_id")
  private RsLocationCategories rsLocationCategories;
}
