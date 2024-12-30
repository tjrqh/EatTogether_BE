package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsCuisineCategories;
import com.project.eatTogether.domain.enums.CuisineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RsCuisineCategoriesRepository extends JpaRepository<RsCuisineCategories, Long> {

//    // RsCuisineCategories에서 RsRestaurant와 연관된 데이터를 미리 가져오는 Fetch Join을 사용하여 성능 최적화
//    @Query("SELECT category FROM RsCuisineCategories category " +
//            "JOIN FETCH category.rsRestaurant restaurant " +  // RsRestaurant를 함께 fetch
//            "WHERE category.rsCuisineCategoryName = :rsCuisineCategoryName " +
//            "AND restaurant.rsState != '폐업'")
//    Page<RsCuisineCategories> findByRsCuisineCategoryName(@Param("rsCuisineCategoryName") String rsCuisineCategoryName, Pageable pageable);

    Page<RsCuisineCategories> findByTypeAndRsRestaurant_RsStateNot(
            CuisineType type,
            String rsState,
            Pageable pageable);

    Page<RsCuisineCategories> findByType(CuisineType type, Pageable pageable);
}
