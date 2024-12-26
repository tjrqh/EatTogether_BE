package com.project.eatTogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(nullable = false)
    private int cartAmount;

    @Column(nullable = false)
    private LocalDateTime cartCreatedAt;

    @Column
    private LocalDateTime cartUpdatedAt;

    @Column
    private LocalDateTime cartDeletedAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
    private RsRestaurant rsRestaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Cart와 Queue 간의 관계를 설정
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")  // Cart가 Queue를 참조하도록 설정
    @JsonIgnore
    private Queue queue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CartStatus cartStatus = CartStatus.ACTIVE;

    @PrePersist
    @PreUpdate
    protected void onUpdateTimestamp() {
        if (cartCreatedAt == null) {
            cartCreatedAt = LocalDateTime.now();
        } else {
            cartUpdatedAt = LocalDateTime.now();
        }
    }
}
