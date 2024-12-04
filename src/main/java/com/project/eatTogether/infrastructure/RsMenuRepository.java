package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsMenuRepository extends JpaRepository<RsMenu, Long> {
    List<RsMenu> findByRsId(Long rsId);
}
