package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsCuisineCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineCategoriesRepository extends JpaRepository<RsCuisineCategories, Long> {
}
