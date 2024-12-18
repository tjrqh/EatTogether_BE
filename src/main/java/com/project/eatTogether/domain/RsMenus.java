package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsMenuId; // 메뉴id

    @Column(nullable = false)
    private String rsMenuName; // 메뉴이름

    @Column
    private String rsMenuDesc; // 메뉴설명

    @Column(nullable = false)
    private String rsMenuPrice; // 메뉴가격

    @Column
    private String rsMenuState; // 메뉴상태

    @Column
    private String rsMenuAppear; // 메뉴노출여부

    @Column
    private String rsMenuPhotoOrigin; // 식당메뉴사진원본명

    @Column
    private String rsMenuPhotoPath; // 식당메뉴사진경로명

    @Column
    private String rsMenuPhotoName; // 식당메뉴사진이름

    @Column
    private boolean isFeatured = false; // 대표메뉴

    @Column(nullable = false)
    private LocalDateTime rsMenuCreatedAt; // 등록일

    @Column
    private LocalDateTime rsMenuUpdatedAt; // 수정일

    @Column
    private LocalDateTime rsMenuDeletedAt; // 삭제일

    @ManyToOne
    @JoinColumn(name = "rs_id")
    private RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "rsMenus")
    private List<QueueOrderItem> queueOrderItems;

    @OneToMany(mappedBy = "rsMenus")
    private List<CartItem> cartItems;

}