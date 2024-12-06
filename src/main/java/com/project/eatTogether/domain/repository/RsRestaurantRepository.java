package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.entity.RsRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RsRestaurantRepository extends JpaRepository<RsRestaurant, Long> {
}
