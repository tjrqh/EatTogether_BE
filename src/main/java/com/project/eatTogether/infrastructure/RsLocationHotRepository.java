package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsLocationCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsLocationHotRepository extends JpaRepository<RsLocationCategories, Long> {
    RsLocationCategories findByRsLocationName(String rsLocationName);
}
