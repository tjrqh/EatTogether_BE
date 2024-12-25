package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsReviewsRepository extends JpaRepository<RsReview, Long> {
    List<RsReview> findByRsRestaurantRsId(Long rsId, Pageable pageable);
}
