package com.project.eatTogether.infrastructure.differed;

import com.project.eatTogether.domain.entity.differed.CuisineCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineCategoriesRepository extends JpaRepository<CuisineCategories, Long> {
}
