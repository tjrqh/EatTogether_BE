package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.entity.differed.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @Column
    private String followingId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member1;

}
