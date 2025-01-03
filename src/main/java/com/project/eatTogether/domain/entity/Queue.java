package com.project.eatTogether.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.entity.differed.Member;
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
public class Queue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @JsonBackReference // avoid circular references in serialization
    private Member member;

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

    // Queue와 QueueOrder의 관계 추가
    @OneToOne(mappedBy = "queue", fetch = FetchType.LAZY)
    private QueueOrder queueOrder;


}
