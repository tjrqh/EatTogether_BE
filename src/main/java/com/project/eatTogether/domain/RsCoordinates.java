package com.project.eatTogether.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsCoordinates {

    @ManyToMany
    @Column(name = "rs_id", nullable = false)
    public Restaurant restaurant;

    @Column
    public Long lat;

    @Column
    public Long rs_long;

    @Column
    public String addr;

}
