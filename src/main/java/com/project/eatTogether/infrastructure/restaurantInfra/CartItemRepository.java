package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}