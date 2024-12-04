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

    @Column
    private int queueOrderItemAmount;

    @Column
    private int queueOrderItemPrice;

    @ManyToOne
    @JoinColumn(name = "rs_menu_id")
    private RsMenus rsMenus;

    @ManyToOne
    @JoinColumn(name = "queue_order_id")
    private QueueOrder queueOrder;

}
