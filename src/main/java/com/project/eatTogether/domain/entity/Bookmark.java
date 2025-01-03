package com.project.eatTogether.domain.entity;

import com.project.eatTogether.domain.entity.baseentity.BaseEntity;
import com.project.eatTogether.domain.entity.differed.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Bookmark extends BaseEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookmarkId;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToOne
  @JoinColumn(name = "memo_id")
  private Memo memo;

  @ManyToOne
  @JoinColumn(name = "rs_id")
  private RsRestaurant rsRestaurant;

}