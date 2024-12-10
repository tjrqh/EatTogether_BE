package com.project.eatTogether.domain.repository;

import com.project.eatTogether.domain.RsReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriteReviewRepository extends JpaRepository<RsReview, Long> {
}