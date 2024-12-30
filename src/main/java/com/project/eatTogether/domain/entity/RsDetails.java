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
public class RsDetails extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsDetailsId;

    @Column(columnDefinition = "TEXT")
    private String rsDescription;

    @Column
    private String corkage;

    @Column
    private String parkInfo;

    @ManyToOne
    @JoinColumn(name = "rs_id", nullable = false)
    private RsRestaurant rsRestaurant;
}
