package com.project.eatTogether.infrastructure.adminInfra;

import com.project.eatTogether.domain.RsRestaurant;
import java.util.List;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantManagingRepository extends JpaRepository<RsRestaurant, Long> {

  List<RsRestaurant> findByRsState(String state/*,Pageable pageable*/);
}
