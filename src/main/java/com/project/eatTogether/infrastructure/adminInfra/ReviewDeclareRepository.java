package com.project.eatTogether.infrastructure.adminInfra;

import com.project.eatTogether.domain.entity.ReviewDeclare;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDeclareRepository extends JpaRepository<ReviewDeclare, Long> {

  @Query("SELECT r FROM ReviewDeclare r WHERE r.reviewDeclareState ='declare'")
  List<ReviewDeclare> findAllByReviewState();

}
