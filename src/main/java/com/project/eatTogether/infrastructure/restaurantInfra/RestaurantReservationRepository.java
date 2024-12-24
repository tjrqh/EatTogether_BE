package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.RsReservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantReservationRepository extends JpaRepository<RsReservation, Long> {

  @Query("select r from RsReservation r where r.rsRestaurant.rsId = :rsId AND r.rsReservationDeletedAt IS Null ")
  List<RsReservation> findByRsRestaurantRsId(Long rsId);

  @Query("SELECT r FROM RsReservation r WHERE r.rsRestaurant.rsId =:rsId AND r.rsReservationDeletedAt IS NULL AND r.rsReservationDate < CURRENT DATE")
  List<RsReservation> findByRsId(Long rsId);

}
