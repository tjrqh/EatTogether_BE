package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.eatTogether.domain.Address;
import com.project.eatTogether.domain.RestaurantStats;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.domain.entity.differed.restaurant.RestaurantApprovalHistory;
import com.project.eatTogether.domain.enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class RsRestaurant extends BaseEntity {

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

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private RestaurantStatus status;

  @Column
  private int rsReviewCount;

  @Column
  private int rsBookmarkCount;

  @Column
  private Double rsAvgRate;

  @Column
  private int rsReservationCount;

  @Column
  @Builder.Default
  private boolean rsDepositRequired = false;  // 예약금 필수 여부, 기본값 false

  @Column
  @Builder.Default
  private Integer rsDepositAmount = 0;  // 예약금 금액, 기본값 0

  @Column(columnDefinition = "TEXT")
  private String rsInfo; // 식당소개

  //식당 소유자
  @ManyToOne
  @JoinColumn(name = "owner_member_id", nullable = false)
  private Member owner;

  //위치 정보
  @Embedded
  private Address address;

  // 줄서기 가능 여부
  @Column
  private boolean rsQueueEnabled = true;

  @Column
  private boolean isPrepaid = false;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<RsReview> rsReviews;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<RsReservation> rsReservations;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<Queue> queues;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<Payment> payments;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<Bookmark> bookmarks;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<RsTable> rsTables;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<Cart> carts;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<QueueOrder> queueOrders;  // 수정된 부분

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<RsRestaurantAmenitiesMapping> rsRestaurantAmenitiesMappings;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<RsMenus> rsMenus;

  @OneToMany(mappedBy = "rsRestaurant", fetch = FetchType.LAZY)
  private List<RsNews> rsNews;

  @OneToOne
  @JoinColumn(name = "rs_coordinates_id")  // Foreign Key 설정
  @JsonManagedReference
  private RsCoordinates rsCoordinates;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rs_cuisine_categories_id", nullable = false)
  private RsCuisineCategories rsCuisineCategories;


  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "rs_document_id")
  private RsDocument rsDocument;

  @ManyToOne
  @JoinColumn(name = "rs_location_categories_id")
  private RsLocationCategories rsLocationCategories;


  @OneToMany(mappedBy = "rsRestaurant")
  private List<TemporarySchedule> temporarySchedules;

  //통계정보
  @Embedded
  private RestaurantStats stats;

//  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//  private List<RestaurantApprovalHistory> approvalHistories;

  // 비즈니스 메서드
  public void updateStatus(RestaurantStatus status) {
    this.status = status;
  }

  public void updateDepositPolicy(boolean required, Integer amount) {
    this.rsDepositRequired = required;
    this.rsDepositAmount = amount;
  }

  public void updateRating() {
    this.rsAvgRate = calculateAverageRating();
  }

  private Double calculateAverageRating() {
    return rsReviews.stream()
            .mapToDouble(RsReview::getRsReviewRate)
            .average()
            .orElse(0.0);
  }

  public static RsRestaurant createRestaurant(
          String rsName,
          String rsPhone,
          Member owner,
          Address address,
          RsDocument rsDocument,
          RsCuisineCategories rsCuisineCategories  // 선택적 파라미터
  ) {
    return RsRestaurant.builder()
            .rsName(rsName)
            .rsPhone(rsPhone)
            .owner(owner)
            .status(RestaurantStatus.PREPARING)
            .address(address)
            .rsDocument(rsDocument)
            .rsCuisineCategories(rsCuisineCategories)  // null이어도 됨
            .build();
  }


  public RsRestaurant(Long rsId, Member owner, String rsName, String rsPhone, String rsTime,
                    RestaurantStatus status, Double rsAvgRate,
                    boolean rsDepositRequired, Integer rsDepositAmount,
                    RsDocument rsDocument, Address address, RsCoordinates rsCoordinates,
                    RsCuisineCategories rsCuisineCategories, RsLocationCategories rsLocationCategories, RestaurantStats stats) {
    this.rsId = rsId;
    this.owner = owner;
    this.rsName = rsName;
    this.rsPhone = rsPhone;
    this.rsTime = rsTime;
    this.status = status;
    this.rsAvgRate = rsAvgRate;
    this.rsDepositRequired = rsDepositRequired;
    this.rsDepositAmount = rsDepositAmount;
    this.rsDocument = rsDocument;
    this.address = address;
    this.rsCoordinates = rsCoordinates;
    this.rsCuisineCategories = rsCuisineCategories;
    this.rsLocationCategories = rsLocationCategories;
    this.stats = stats;
  }

  // 카테고리 설정 메소드
  public void setCuisineCategory(RsCuisineCategories category) {
    this.rsCuisineCategories = category;
  }


}
