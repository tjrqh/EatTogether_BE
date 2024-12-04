package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "rsMenus")
    private Long rsMenuId;

    @Column(nullable = false)
    private String rsMenuName;

    @Column
    private String rsMenuDesc;

    @Column(nullable = false)
    private String rsMenuPrice;

    @Column
    private String rsMenuState;

    @Column
    private String rsMenuAppear;

    @Column
    private String rsMenuPhotoOrigin;

    @Column
    private String rsMenuPhotoPath;

    @Column
    private String rsMenuPhotoName;

    @Column(nullable = false)
    private LocalDateTime rsMenuCreatedAt;

    @Column
    private LocalDateTime rsMenuUpdatedAt;

    @Column
    private LocalDateTime rsMenuDeletedAt;


    @ManyToOne
    @JoinColumn(name = "rs_id")
    private RsRestaurant rsRestaurant;

    @OneToMany(mappedBy = "rsMenu")
    private List<QueueOrderItem> queueOrderItems;

    @OneToMany(mappedBy = "rsMenu")
    private List<CartItem> cartItems;

}
