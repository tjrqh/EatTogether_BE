package com.project.eatTogether.application.dto.adminDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserManagingReadResponse {

  private Long id;
  private String userName;
  private String userNickName;
  private String userState;
  private String deletedAt;

  }
