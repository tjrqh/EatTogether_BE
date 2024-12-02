package com.project.eatTogether.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookMark {

    @ManyToMany
    @Column(name = "user_id", nullable = false)
    public User user;

    @ManyToMany
    @Column(name = "rs_id", nullable = false)
    public Restaurant restaurant;

    @ManyToMany
    @Column(name = "id")
    public Memo memo;

    @ManyToMany
    @Column(name = "id")
    private UserRole userRole;

    @Column // 등록일
    public LocalDate created_at;

    @Column // 수정일
    public LocalDate updated_at;

    @Column // 삭제일
    public LocalDate deleted_at;

}
