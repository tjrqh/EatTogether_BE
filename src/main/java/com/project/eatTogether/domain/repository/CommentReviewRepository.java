package com.project.eatTogether.domain.repository;

import com.project.eatTogether.application.dto.CommentRsReviewDTO;
import com.project.eatTogether.domain.RsReview;
import com.project.eatTogether.domain.RsReviewComment;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentReviewRepository extends JpaRepository<RsReviewComment, Long> {

    @QueryHints(value = { @QueryHint(name = "javax.persistence.cache.storeMode", value = "REFRESH") })
    @Query("SELECT r FROM RsReviewComment r WHERE r.id = :id")
    RsReviewComment refresh(@Param("id") Long id);

    List<RsReviewComment> findByRsReview(RsReview rsReview);

    //Optional<Object> findById(Optional<Long> rsParentCommentId);
}



