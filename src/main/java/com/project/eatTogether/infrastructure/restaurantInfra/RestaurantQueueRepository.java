package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.Queue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantQueueRepository extends JpaRepository<Queue,Long> {

  @Query("SELECT q FROM Queue q WHERE q.rsRestaurant.rsId = :id AND q.queueState = 'waiting'")
  List<Queue> waitingFindByRsRestaurantRsId(Long id);

  @Query("SELECT q FROM Queue q WHERE q.rsRestaurant.rsId = :id AND q.queueState != 'waiting'")
  List<Queue> notWaitingFindByRsRestaurantRsId(Long id);


}
