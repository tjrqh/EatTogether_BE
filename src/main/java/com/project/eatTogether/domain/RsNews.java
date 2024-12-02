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
public class RsNews {

    @ManyToMany
    @Column(name = "rs_id" ,nullable = false)
    public Restaurant restaurant; // 식당id

    @Column
    public String content;  // 식당소식내용

    @Column
    public LocalDate published_at;  // 작성일

    @Column
    public LocalDate updated_at;  // 수정일

    @Column
    public LocalDate deleted_at;  // 삭제일

}
