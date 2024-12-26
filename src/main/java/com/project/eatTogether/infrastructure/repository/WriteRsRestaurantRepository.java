package com.project.eatTogether.infrastructure.repository;

import com.project.eatTogether.domain.entity.RsRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteRsRestaurantRepository extends JpaRepository<RsRestaurant, Long> {
}
