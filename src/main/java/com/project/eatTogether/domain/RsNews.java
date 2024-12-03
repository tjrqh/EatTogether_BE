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
public class RsNews {

    @ManyToOne
    @JoinColumn(name = "rs_id" , nullable = false)
    public RsRestaurant rs_restaurant; // 식당id

    @Column
    public String rs_news_content;  // 식당소식내용

    @Column
    public LocalDate rs_news_published_at;  // 작성일

    @Column
    public LocalDate rs_news_updated_at;  // 수정일

    @Column
    public LocalDate rs_news_deleted_at;  // 삭제일

}
