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
public class RsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 테이블번호
    public String id;

    @ManyToMany // 식당아디
    @Column(name = "rs_id")
    public Restaurant restaurant;

    @Column // 테이블크기
    public Byte size;

    @Column // 테이블상태
    public String state;

    @Column // 등록일
    public LocalDate created_at;

    @Column // 수정일
    public LocalDate updated_at;

    @Column // 삭제일
    public LocalDate deleted_at;

}
