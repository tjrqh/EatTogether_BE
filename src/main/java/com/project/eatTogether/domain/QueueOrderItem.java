package com.project.eatTogether.domain;

import jakarta.persistence.*;
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
    public Long id;

    @OneToOne
    @Column(name = "id")
    public QueueOrderItem queueOrderItem;

    @ManyToMany
    @Column(name = "id")
    public RsMenus rsMenus;

    @Column
    public Long amount;

    @Column
    public Long price;

}
