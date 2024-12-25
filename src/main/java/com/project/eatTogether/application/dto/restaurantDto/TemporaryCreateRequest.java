package com.project.eatTogether.application.dto.restaurantDto;

import com.project.eatTogether.domain.enums.Temporary;
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
public class TemporaryCreateRequest {
  private Long id;
  private Temporary temporary;
  private String temporaryHours;
  private LocalDate temporaryDate;
  private String explanation;

  public TemporaryCreateRequest(Temporary temporary) {
    this.temporary = temporary;
  }
}
