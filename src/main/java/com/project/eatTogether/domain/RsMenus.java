package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RsMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;;

    @ManyToMany
    @Column(name= "rs_id", nullable = false)
    public Restaurant restaurant;

    @Column(nullable = false)
    public String name;

    @Column
    public String desc;

    @Column(nullable = false)
    public String price;

    @Column
    public Boolean state;

    @Column
    public Boolean appear;

    @Column
    public String photo_origin;

    @Column
    public String photo_path;

    @Column
    public String photo_name;

    @Column
    public LocalDate created_at;

    @Column
    public LocalDate updated_at;

    @Column
    public LocalDate deleted_at;

}
