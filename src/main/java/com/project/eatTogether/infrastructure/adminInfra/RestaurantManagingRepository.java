package com.project.eatTogether.infrastructure.adminInfra;

import com.project.eatTogether.domain.RsRestaurant;
import java.util.List;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantManagingRepository extends JpaRepository<RsRestaurant, Long> {


  Page<RsRestaurant> findByRsState(String state, Pageable pageable);
  List<RsRestaurant> findByRsState(String state);
}
