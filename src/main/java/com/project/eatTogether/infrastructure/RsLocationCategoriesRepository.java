package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsLocationCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsLocationCategoriesRepository extends JpaRepository<RsLocationCategories, Long> {
    RsLocationCategories findByRsRestaurantRsId(Long rsId);
}
