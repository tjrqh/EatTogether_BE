package com.project.eatTogether.domain.repository;

import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface



CommentReviewRepository extends JpaRepository<CommentRsReviewDTO, Long> {
}
