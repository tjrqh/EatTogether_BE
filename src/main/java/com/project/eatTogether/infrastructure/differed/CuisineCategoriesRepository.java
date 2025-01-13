package com.project.eatTogether.infrastructure.differed;

import com.project.eatTogether.application.dto.differed.restaurant.CategoryDto;
import com.project.eatTogether.domain.entity.differed.CuisineCategories;
import com.project.eatTogether.domain.enums.CuisineType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineCategoriesRepository extends JpaRepository<CuisineCategories, Long> {

  List<CuisineCategories> findByType(CuisineType type);


}
