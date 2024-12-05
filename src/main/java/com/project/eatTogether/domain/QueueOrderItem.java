package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.awt.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class QueueOrderItem {
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
