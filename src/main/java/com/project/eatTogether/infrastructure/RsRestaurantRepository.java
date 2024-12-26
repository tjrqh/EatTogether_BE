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

    // 리뷰 개수 오름차순
    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsReviewCount ASC")
    Page<RsRestaurant> findByReviewCountAsc(Pageable pageable);

    // 리뷰 개수 내림차순
    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsReviewCount DESC")
    Page<RsRestaurant> findByReviewCountDesc(Pageable pageable);

    // 평점 오름차순
    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsAvgRate ASC")
    Page<RsRestaurant> findByAvgRateAsc(Pageable pageable);

    // 평점 내림차순
    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsAvgRate DESC")
    Page<RsRestaurant> findByAvgRateDesc(Pageable pageable);

    // 북마크 개수 오름차순
    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsBookmarkCount ASC")
    Page<RsRestaurant> findByBookmarkCountAsc(Pageable pageable);

    // 북마크 개수 내림차순
    @Query("SELECT r FROM RsRestaurant r ORDER BY r.rsBookmarkCount DESC")
    Page<RsRestaurant> findByBookmarkCountDesc(Pageable pageable);

    List<RsRestaurant> findByRsId(Long id);

}
