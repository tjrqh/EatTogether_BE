package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsNewsRepository extends JpaRepository<RsNews, Long> {
    List<RsNews> findByRsRestaurantRsId(Long rsId);
}
