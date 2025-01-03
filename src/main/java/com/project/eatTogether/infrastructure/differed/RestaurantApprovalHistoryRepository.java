package com.project.eatTogether.infrastructure.differed;

import com.project.eatTogether.domain.entity.differed.restaurant.RestaurantApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantApprovalHistoryRepository extends JpaRepository<RestaurantApprovalHistory, Long> {

}
