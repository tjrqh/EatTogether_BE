package com.project.eatTogether.domain.entity;

import com.project.eatTogether.application.dto.restaurantDto.TemporaryCreateRequest;
import com.project.eatTogether.domain.enums.Temporary;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class TemporarySchedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Temporary temporary;
  private String temporaryHours;
  private LocalDate temporaryDate;
  private String explanation;
  private LocalDateTime createdAt;
  private LocalDateTime deletedAt;

  @ManyToOne
  @JoinColumn(name="rs_id" , updatable = false)
  private RsRestaurant rsRestaurant;

  @PrePersist
      @PreUpdate
      protected void onUpdateTimestamp() {
          if (createdAt == null) {
            createdAt = LocalDateTime.now();
          }
      }


  public static TemporarySchedule toEntity(TemporaryCreateRequest dto, RsRestaurant rsRestaurant) {
      TemporarySchedule temporarySchedule = new TemporarySchedule();
      temporarySchedule.setTemporary(dto.getTemporary());
      temporarySchedule.setTemporaryHours(dto.getTemporaryHours());
      temporarySchedule.setTemporaryDate(dto.getTemporaryDate());
      temporarySchedule.setExplanation(dto.getExplanation());
      temporarySchedule.setRsRestaurant(rsRestaurant);  // RsRestaurant 설정
      return temporarySchedule;
  }

  public static List<TemporarySchedule> readEntity(List<TemporarySchedule> temporary) {
    List<TemporarySchedule> list = new ArrayList<>(temporary);
      return list;
  }

}
