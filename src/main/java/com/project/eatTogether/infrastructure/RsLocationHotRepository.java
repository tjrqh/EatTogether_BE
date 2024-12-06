package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsLocationCategories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsLocationHotRepository extends JpaRepository<RsLocationCategories, Long> {
    RsLocationCategories findByRsLocationName(String rsLocationName);
    Page<RsLocationCategories> findByRsLocationName(String rsLocationName, Pageable pageable);
}
