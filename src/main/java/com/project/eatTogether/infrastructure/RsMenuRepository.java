package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsMenus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsMenuRepository extends JpaRepository<RsMenus, Long> {
    Page<RsMenus> findByRsRestaurantRsId(Long rsId, Pageable pageable);
}
