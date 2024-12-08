package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsMenus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsMenuRepository extends JpaRepository<RsMenus, Long> {
    List<RsMenus> findByRsRestaurantRsId(Long rsId, PageRequest pageRequest);
}
