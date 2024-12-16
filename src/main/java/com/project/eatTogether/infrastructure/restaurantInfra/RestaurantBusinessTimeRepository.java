package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.RsRestaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantBusinessTimeRepository extends JpaRepository<RsRestaurant , Long> {

  @Query("SELECT r.rsTime FROM RsRestaurant r WHERE r.rsId = :rsId")
  List<String> findByRestaurantRsTime(Long rsId);


}
