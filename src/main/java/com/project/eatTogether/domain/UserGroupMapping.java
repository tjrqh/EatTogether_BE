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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userGroupMappingId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name ="group_id")
  private RsGroup rsGroup;

  @Column(nullable = false)
  private LocalDate userGroupMappingCreatedAt;

  @Column
  private LocalDate userGroupMappingUpdatedAt;

  @Column
  private LocalDate userGroupMappingDeletedAt;

}
