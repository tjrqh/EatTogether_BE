package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsRestaurantInfoRepository extends JpaRepository<RsDetails, Long> {
    List<RsDetails> findByRsRestaurantRsId(Long rsId);
}
