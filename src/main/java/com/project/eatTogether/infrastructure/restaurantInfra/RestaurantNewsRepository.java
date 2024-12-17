package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.RsNews;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantNewsRepository extends JpaRepository<RsNews, Long> {

  @Query("SELECT n FROM RsNews n WHERE n.rsRestaurant.rsId = :rsId AND n.rsNewsDeletedAt IS NULL")
  List<RsNews> findByRestaurantRsId(Long rsId);

}
