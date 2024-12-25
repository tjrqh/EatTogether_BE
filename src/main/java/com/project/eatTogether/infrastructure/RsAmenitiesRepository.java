package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsAmenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RsAmenitiesRepository extends JpaRepository<RsAmenities, Long> {
}