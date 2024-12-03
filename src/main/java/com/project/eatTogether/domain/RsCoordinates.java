package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsCoordinates {

    @ManyToOne
    @JoinColumn(name = "rs_id", nullable = false)
    public RsRestaurant rs_restaurant;

    @Column
    public Long restaurant_lat;

    @Column
    public Long restaurant_long;

    @Column
    public String restaurant_addr;

}
