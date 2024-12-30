package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<RsRestaurant, Long>{
//    boolean existByRsName(String rsName);
    @Query("SELECT r FROM RsRestaurant r LEFT JOIN FETCH r.rsCoordinates LEFT JOIN FETCH r.owner")
    List<RsRestaurant> findAllWithCoordinates();

    List<RsRestaurant> findByOwner_UserId(Long userId);  // 점주 ID로 식당 조회

    Optional<RsRestaurant> findByOwner(User owner);
}
