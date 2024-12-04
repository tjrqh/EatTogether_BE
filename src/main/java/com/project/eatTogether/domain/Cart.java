package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(nullable = false)
    private int basketAmount;

    @Column(nullable = false)
    private LocalDateTime basketCreatedAt;

    @Column
    private LocalDateTime basketUpdatedAt;

    @Column
    private LocalDateTime basketDeletedAt;


    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "rs_restaurant_id")
    private RsRestaurant rsRestaurant;

    @OneToOne(mappedBy = "cart")
    private User user;

}
