package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class QueueOrderItem  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueOrderItemId;

    @Column(nullable = false)
    private int queueOrderItemAmount;

    @Column(nullable = false)
    private int queueOrderItemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rs_menu_id")
    private RsMenus rsMenus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_order_id", nullable = false)
    private QueueOrder queueOrder;
}
