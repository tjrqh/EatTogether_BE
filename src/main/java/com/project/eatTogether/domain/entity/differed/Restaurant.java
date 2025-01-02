package com.project.eatTogether.domain.entity.differed;

import com.project.eatTogether.domain.Address;
import com.project.eatTogether.domain.RestaurantStats;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.RestaurantStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@ToString(exclude = {"owner", "reviews", "reservations", "bookmarks"})
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column
    private String openingHours; //영업시간

    @Column
    private String parkingInfo; //주차정보

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @Column
    private Double avgRate; //평균 평점


    @Column
    @Builder.Default
    private boolean depositRequired = false; //예약금 필수 여부

    @Column
    @Builder.Default
    private Integer depositAmount = 0; //예약금 금액

    //식당 소유자
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Member owner;

    //사업자 등록 정보
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id")
    private Document document;

    //위치 정보
    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuisine_categories_id", nullable = false)
    private CuisineCategories cuisineCategories;

    @OneToOne
    @JoinColumn(name = "location_categories_id")
    private LocationCategories locationCategories;

    //통계정보
    @Embedded
    private RestaurantStats stats;

    //예약목록
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    @Builder.Default
    private List<Reservation> reservations;
    
    //리뷰 목록
    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;

//    북마크 목록
//    @OneToMany(mappedBy = "rsRestaurant")
//    private List<Bookmark> bookmarks;

//    //메뉴 목록
//    @OneToMany(mappedBy = "rsRestaurant")
//    private List<RsMenus> rsMenus;

//    테이블 목록
    //  @OneToMany(mappedBy = "rsRestaurant")
//  private List<RsTable> rsTables;

    // 비즈니스 메서드
    public void updateStatus(RestaurantStatus status) {
        this.status = status;
    }

    public void updateDepositPolicy(boolean required, Integer amount) {
        this.depositRequired = required;
        this.depositAmount = amount;
    }

    public void updateRating() {
        this.avgRate = calculateAverageRating();
    }

    private Double calculateAverageRating() {
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }

    public static Restaurant createRestaurant(
            String name,
            String phone,
            Member owner,
            Address address,
            Document document,
            CuisineCategories cuisineCategories  // 선택적 파라미터
    ) {
        return Restaurant.builder()
                .name(name)
                .phone(phone)
                .owner(owner)
                .status(RestaurantStatus.PREPARING)
                .address(address)
                .document(document)
                .cuisineCategories(cuisineCategories)  // null이어도 됨
                .build();
    }


    public Restaurant(Long id, Member owner, String name, String phone, String parkingInfo, String openingHours,
                      RestaurantStatus status, Double avgRate,
                      boolean depositRequired, Integer depositAmount,
                      Document document, Address address, Coordinates coordinates,
                      CuisineCategories cuisineCategories, LocationCategories locationCategories, RestaurantStats stats) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.phone = phone;
        this.parkingInfo = parkingInfo;
        this.openingHours = openingHours;
        this.status = status;
        this.avgRate = avgRate;
        this.depositRequired = depositRequired;
        this.depositAmount = depositAmount;
        this.document = document;
        this.address = address;
        this.coordinates = coordinates;
        this.cuisineCategories = cuisineCategories;
        this.locationCategories = locationCategories;
        this.stats = stats;
    }

    // 카테고리 설정 메소드
    public void setCuisineCategory(CuisineCategories category) {
        this.cuisineCategories = category;
    }



//  @OneToMany(mappedBy = "rsRestaurant")
//  private List<Queue> queues;

//  @OneToMany(mappedBy = "rsRestaurant")
//  private List<Payment> payments;



//  @OneToMany(mappedBy = "rsRestaurant")
//  private List<Cart> carts;

//  @OneToMany(mappedBy = "rsRestaurant")
//  private List<QueueOrder> queueOrders;
//
//  @OneToMany(mappedBy = "rsRestaurant")
//  private List<RsRestaurantAmenitiesMapping> rsRestaurantAmenitiesMappings;
//

//  @OneToMany(mappedBy = "rsRestaurant")
//  private List<RsNews> rsNews;
//



}

