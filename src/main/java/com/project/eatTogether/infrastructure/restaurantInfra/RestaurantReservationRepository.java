package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.RsReservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantReservationRepository extends JpaRepository<RsReservation, Long> {

  List<RsReservation> findByRsRestaurantRsId(Long restaurantId);

}
