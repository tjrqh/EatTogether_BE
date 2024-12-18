package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.RsRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriteRsRestaurantRepository extends JpaRepository<RsRestaurant, Long> {
}
