package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}