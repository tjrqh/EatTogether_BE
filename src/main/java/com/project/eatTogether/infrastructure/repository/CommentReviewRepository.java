package com.project.eatTogether.infrastructure.repository;

import com.project.eatTogether.domain.entity.RsReview;
import com.project.eatTogether.domain.entity.RsReviewComment;
import com.project.eatTogether.domain.entity.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentReviewRepository extends JpaRepository<RsReviewComment, Long> {

    @QueryHints(value = { @QueryHint(name = "javax.persistence.cache.storeMode", value = "REFRESH") })
    @Query("SELECT r FROM RsReviewComment r WHERE r.rsCommentId = :id")
    RsReviewComment refresh(@Param("id") Long id);

    List<RsReviewComment> findByRsReview(RsReview rsReview);

    List<RsReviewComment> findByUser(User user);

    //List<RsReviewComment> findByRsCommentId(RsReviewComment rsCommentId);
    //Optional<Object> findById(Optional<Long> rsParentCommentId);
}



