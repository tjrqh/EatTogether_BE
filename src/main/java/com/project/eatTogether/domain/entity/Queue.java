package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Queue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference // avoid circular references in serialization
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_id", nullable = false)
    @JsonBackReference  // avoid circular references in serialization
    private RsRestaurant rsRestaurant;

    @Column(nullable = false)
    private Integer queueNumber;

    @Column(nullable = false)
    private LocalDate queueDate;

    @Column(nullable = false)
    private LocalTime queueTime;

    @Column(nullable = false)
    private String queueState;

    @Column(nullable = false)
    private LocalDateTime queueCreatedAt;

    @Column
    private LocalDateTime queueUpdatedAt;

    @Column
    private LocalDateTime queueDeletedAt;

    // Queue와 QueueOrder의 관계 추가
    @OneToOne(mappedBy = "queue", fetch = FetchType.LAZY)
    private QueueOrder queueOrder;

    @PrePersist
    @PreUpdate
    protected void onUpdateTimestamp() {
        if (queueCreatedAt == null) {
            queueCreatedAt = LocalDateTime.now();
        } else {
            queueUpdatedAt = LocalDateTime.now();
        }
    }
}
