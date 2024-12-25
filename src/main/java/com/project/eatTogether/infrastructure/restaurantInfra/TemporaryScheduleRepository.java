package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.entity.TemporarySchedule;
import com.project.eatTogether.domain.enums.Temporary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemporaryScheduleRepository extends JpaRepository<TemporarySchedule, Long> {

  @Query("select t from TemporarySchedule t WHERE t.rsRestaurant.rsId = :rsId AND t.temporary = :temporary AND t.deletedAt IS NULL ORDER BY t.temporaryDate DESC")
  List<TemporarySchedule> findByRsRestaurantRsId(Long rsId, Temporary temporary);



}
