package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsLocationCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RsLocationCategoriesRepository extends JpaRepository<RsLocationCategories, Long> {
    @Query("SELECT category FROM RsLocationCategories category WHERE category.rsLocationName = :rsLocationName AND category.rsRestaurant.rsState != '폐업'")
    Page<RsLocationCategories> findByRsLocationName(String rsLocationName, Pageable pageable);

    RsLocationCategories findByRsRestaurantRsId(Long rsId);
}
