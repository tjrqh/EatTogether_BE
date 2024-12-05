package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsCuisineCategories;
import com.project.eatTogether.domain.RsRestaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsCuisineCategoriesRepository extends JpaRepository<RsCuisineCategories, Long> {
    RsCuisineCategories findByRsCuisineCategoryName(String rsCuisineCategoryName);
    Page<RsRestaurant> findByRsCuisineCategoryName(String rsCuisineCategoryName , Pageable pageable);
}
