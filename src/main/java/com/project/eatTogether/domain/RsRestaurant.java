package com.project.eatTogether.domain;

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


  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<RsReview> rsReviews;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<RsReservation> rsReservations;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<Queue> queues;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<Payment> payments;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<Bookmark> bookmarks;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<RsTable> rsTables;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<Cart> carts;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<QueueOrder> queueOrders;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<RsRestaurantAmenitiesMapping> rsRestaurantAmenitiesMappings;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<RsMenus> rsMenus;

  @OneToMany(mappedBy = "rsRestaurant",fetch = FetchType.LAZY)
  private List<RsNews> rsNews;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rs_coordinates_id")
  private RsCoordinates rsCoordinates;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rs_cuisine_categories_id")
  private RsCuisineCategories rsCuisineCategories;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rs_document_id")
  private RsDocument rsDocument;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rs_location_categories_id")
  private RsLocationCategories rsLocationCategories;
}
