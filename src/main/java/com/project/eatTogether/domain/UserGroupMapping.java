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
public class UserGroupMapping {

    @ManyToMany
    @Column(name = "user_id" ,nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "id" ,nullable = false)
    public Group group;

    @Column
    public LocalDate created_at;

    @Column
    public LocalDate updated_at;

    @Column
    public LocalDate deleted_at;

}
