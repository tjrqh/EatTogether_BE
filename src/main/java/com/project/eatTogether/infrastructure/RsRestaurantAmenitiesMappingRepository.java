package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsRestaurantAmenitiesMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RsRestaurantAmenitiesMappingRepository extends JpaRepository<RsRestaurantAmenitiesMapping, Long> {
    List<RsRestaurantAmenitiesMapping> findByRsId(Long rsId);
}
