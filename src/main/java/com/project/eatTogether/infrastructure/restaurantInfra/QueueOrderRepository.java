package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.entity.QueueOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QueueOrderRepository extends JpaRepository<QueueOrder, Long> {
    Optional<QueueOrder> findByQueueQueueId(Long queueId);  // queueId로 QueueOrder 조회

}