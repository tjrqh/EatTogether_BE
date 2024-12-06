package com.project.eatTogether.application.dto.adminDto;


import java.time.LocalDate;
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
public class ReviewDeclareStateSearchReadResponse {

  private Long reviewDeclareId;
  private String reviewDeclareContent;
  private String reviewDeclareState;
  private LocalDate reviewDeclareCreatedAt;



}
