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
public class RsNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsNewsId;

    @Column
    private String rsNewsContent;

    @Column
    private LocalDateTime rsNewsPublishedCreatedAt;

    @Column
    private LocalDateTime rsNewsUpdatedAt;

    @Column
    private LocalDateTime rsNewsDeletedAt;

    @ManyToOne
    @JoinColumn(name = "rs_Id")
    private RsRestaurant rsRestaurant;

}
