package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsCoordinatesRepository extends JpaRepository<RsCoordinates, Long> {
    RsCoordinates findByRsId(Long rsId);
}
