package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.entity.QueueOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueOrderItemRepository extends JpaRepository<QueueOrderItem, Long> {
}