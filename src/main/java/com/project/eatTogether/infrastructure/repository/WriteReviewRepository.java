package com.project.eatTogether.infrastructure.repository;

import com.project.eatTogether.domain.entity.RsReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteReviewRepository extends JpaRepository<RsReview, Long> {
}