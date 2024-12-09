package com.project.eatTogether.domain;

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
public class Bookmark {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookmarkId;

  @Column(nullable = false)
  private LocalDateTime bookmarkCreatedAt;

  @Column
  private LocalDateTime bookmarkUpdatedAt;

  @Column
  private LocalDateTime bookmarkDeletedAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToOne
  @JoinColumn(name = "memo_id")
  private Memo memo;

  @ManyToOne
  @JoinColumn(name = "rs_id")
  private RsRestaurant rsRestaurant;

}