package com.project.eatTogether.infrastructure.adminInfra;

import com.project.eatTogether.domain.entity.RsRestaurant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantManagingRepository extends JpaRepository<RsRestaurant, Long> {


  Page<RsRestaurant> findByRsState(String state, Pageable pageable);
  List<RsRestaurant> findByRsState(String state);
  List<RsRestaurant> findByRsNameContaining(String rsName);
}
