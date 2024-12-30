package com.project.eatTogether.domain;

import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.enums.RestaurantStatus;
import jakarta.persistence.*;

@Entity
public class RestaurantApprovalHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RsRestaurant restaurant;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;  // 변경된 상태

    @Column(length = 500)
    private String reason;      // 상태 변경 사유

    @Column
    private String actionBy;    // 처리자 정보
}
