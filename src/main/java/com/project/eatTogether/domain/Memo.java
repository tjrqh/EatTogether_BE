package com.project.eatTogether.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memoId;

    @Column
    private String memoContent;

    @Column
    private LocalDateTime memoCreatedAt;

    @Column
    private LocalDateTime memoUpdatedAt;

    @Column
    private LocalDateTime memoDeletedAt;

    @OneToOne(mappedBy = "memo")
    private Bookmark bookMark;


}
