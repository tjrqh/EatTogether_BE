package com.project.eatTogether.domain.repository;

import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import com.project.eatTogether.domain.RsReview;
import com.project.eatTogether.domain.RsReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReviewRepository extends JpaRepository<RsReviewComment, Long> {
    List<RsReviewComment> findByRsReview(RsReview rsReview);
}



