package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsTableId;

    @Column
    private int rsTableSize;

    @Column
    private String rsTableState;

    @Column
    private LocalDateTime rsTableCreatedAt;

    @Column
    private LocalDateTime rsTableUpdatedAt;

    @Column
    private LocalDateTime rsTableDeletedAt;


    @ManyToOne
    @JoinColumn(name = "rs_id")
    private RsRestaurant rsRestaurant;
}
