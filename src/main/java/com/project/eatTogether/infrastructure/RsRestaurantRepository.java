package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsRestaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsRestaurantRepository extends JpaRepository<RsRestaurant, Long> {

    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsReviewCount ASC")
    List<RsRestaurant> findByReviewCountAsc(Pageable pageable);

    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsReviewCount DESC")
    List<RsRestaurant> findByReviewCountDesc(Pageable pageable);

    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsAvgRate ASC")
    List<RsRestaurant> findByAvgRatingAsc(Pageable pageable);

    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsAvgRate DESC")
    List<RsRestaurant> findByAvgRatingDesc(Pageable pageable);

    List<RsRestaurant> findByRsId(Long id);




}
