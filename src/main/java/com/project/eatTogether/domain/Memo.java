package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String content;

    @Column // 등록일
    public LocalDate created_at;

    @Column // 수정일
    public LocalDate updated_at;

    @Column // 삭제일
    public LocalDate deleted_at;

}
