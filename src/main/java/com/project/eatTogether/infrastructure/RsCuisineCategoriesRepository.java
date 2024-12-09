package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsCuisineCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RsCuisineCategoriesRepository extends JpaRepository<RsCuisineCategories, Long> {

    @Query("SELECT category FROM RsCuisineCategories category WHERE category.rsCuisineCategoryName = :rsCuisineCategoryName AND category.rsRestaurant.rsState != '폐업'")
    Page<RsCuisineCategories> findByRsCuisineCategoryName(@Param("rsCuisineCategoryName") String rsCuisineCategoryName, Pageable pageable);
}
