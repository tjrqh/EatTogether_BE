package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
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

  @Column(nullable = false)
  private String rsName;

  @Column(nullable = false)
  private String rsPhone;

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

  @Column
  private boolean rsDepositRequired = false;  // 예약금 필수 여부, 기본값 false

  @Column
  private Integer rsDepositAmount = 0;  // 예약금 금액, 기본값 0

  @Column(columnDefinition = "TEXT")
  private String rsInfo; // 식당소개


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

  @OneToOne
  @JoinColumn(name = "rs_coordinates_id")  // Foreign Key 설정
  @JsonManagedReference
  private RsCoordinates rsCoordinates;

  @OneToMany(mappedBy = "rsRestaurant")
  private List<RsCuisineCategories> rsCuisineCategories;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rs_document_id")
  private RsDocument rsDocument;

  @ManyToOne
  @JoinColumn(name = "rs_location_categories_id")
  private RsLocationCategories rsLocationCategory;

  @Column(nullable = false)
  public LocalDateTime rsRestaurantCreatedAt;

  @Column
  public LocalDateTime rsRestaurantUpdatedAt;

  @Column
  public LocalDateTime rsRestaurantDeletedAt;

}
