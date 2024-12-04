package com.project.eatTogether.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userRoleId;

  @Column(nullable = false)
  private String userRoleName;

  @Column
  private LocalDate userRoleCreatedAt;

  @Column
  private LocalDate userRoleUpdatedAt;

  @Column
  private LocalDate userRoleDeletedAt;

  @OneToOne(mappedBy = "userRole")
  private User user;

}
