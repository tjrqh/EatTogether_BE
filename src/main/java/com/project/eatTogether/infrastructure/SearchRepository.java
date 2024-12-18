package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsRestaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends JpaRepository<RsRestaurant, Long> {

    // 식당 이름으로 검색
    @Query("SELECT r FROM RsRestaurant r WHERE LOWER(r.rsName) LIKE LOWER(CONCAT('%', :rsName, '%'))")
    Page<RsRestaurant> findByRsNameContainingIgnoreCase(String rsName, Pageable pageable);

    // 카테고리 이름으로 검색
    @Query("SELECT r FROM RsRestaurant r JOIN r.rsCuisineCategories c WHERE LOWER(c.rsCuisineCategoryName) LIKE LOWER(CONCAT('%', :cuisineCategoryName, '%'))")
    Page<RsRestaurant> findByCuisineCategoryNameContainingIgnoreCase(String cuisineCategoryName, Pageable pageable);

    // 위치 이름으로 검색
    @Query("SELECT r FROM RsRestaurant r JOIN r.rsLocationCategory l WHERE LOWER(l.rsLocationName) LIKE LOWER(CONCAT('%', :locationName, '%'))")
    Page<RsRestaurant> findByLocationNameContainingIgnoreCase(String locationName, Pageable pageable);
}
