package com.project.eatTogether.infrastructure.repository;

import com.project.eatTogether.domain.entity.RsReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteReviewRepository extends JpaRepository<RsReview, Long> {

  @Query("SELECT r from RsReview  r Where r.rsRestaurant.rsId = :id AND r.rsReviewState <> 'declare' AND r.deletedAt IS NULL ")
  List<RsReview> findByRsRestaurantRsId(Long id);
}