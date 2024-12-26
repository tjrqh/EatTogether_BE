package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class QueueOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueOrderId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_id")
    private Queue queue;

    @OneToMany(mappedBy = "queueOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QueueOrderItem> queueOrderItems;

    @Column(nullable = true)
    private String queueOrderRequestMemo;

    @Column(nullable = true)
    private LocalDateTime queueOrderCreatedAt;

    @Column(nullable = true)
    private LocalDateTime queueOrderUpdatedAt;

    @Column(nullable = true)
    private LocalDateTime orderDateTime;

    @Column(nullable = true)
    private String orderStatus;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)  // Payment 객체 추가
    @JoinColumn(name = "payment_id")   // payment_id 컬럼과 연결
    private Payment payment;  // Payment 객체 필드 추가

    @ManyToOne(fetch = FetchType.LAZY)  // RsRestaurant와의 관계 추가
    @JoinColumn(name = "rs_id")  // rs_id 컬럼과 연결
    private RsRestaurant rsRestaurant;  // RsRestaurant 객체 필드 추가

    @ManyToOne(fetch = FetchType.LAZY)  // User와의 관계 추가
    @JoinColumn(name = "user_id")  // user_id 컬럼과 연결
    private User user;  // User 객체 필드 추가

    @PrePersist
    @PreUpdate
    protected void onUpdateTimestamp() {
        if (queueOrderCreatedAt == null) {
            queueOrderCreatedAt = LocalDateTime.now();
        } else {
            queueOrderUpdatedAt = LocalDateTime.now();
        }
    }
}
