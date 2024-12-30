package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RestaurantApprovalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantApprovalHistoryRepository extends JpaRepository<RestaurantApprovalHistory, Long> {

}
