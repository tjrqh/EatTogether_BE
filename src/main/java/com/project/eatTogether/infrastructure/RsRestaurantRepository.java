package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsRestaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsRestaurantRepository extends JpaRepository<RsRestaurant, Long> {
    Page<RsRestaurant> findByCategoryName(String categoryName, Pageable pageable);
}
