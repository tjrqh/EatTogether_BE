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
    @OneToMany(mappedBy = "re_menus")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long rs_menu_id;;

    @ManyToOne
    @JoinColumn(name= "rs_id", nullable = false)
    public RsRestaurant rs_restaurant;

    @Column(nullable = false)
    public String rs_menu_name;

    @Column
    public String rs_menu_desc;

    @Column(nullable = false)
    public String rs_menu_price;

    @Column
    public Boolean rs_menu_state;

    @Column
    public Boolean rs_menu_appear;

    @Column
    public String rs_menu_photo_origin;

    @Column
    public String rs_menu_photo_path;

    @Column
    public String rs_menu_photo_name;

    @Column
    public LocalDate rs_menu_created_at;

    @Column
    public LocalDate rs_menu_updated_at;

    @Column
    public LocalDate rs_menu_deleted_at;

}
