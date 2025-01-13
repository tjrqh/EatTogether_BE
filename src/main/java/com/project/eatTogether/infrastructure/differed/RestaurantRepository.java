package com.project.eatTogether.infrastructure.differed;

import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.domain.entity.differed.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
//    boolean existByRsName(String rsName);
    @Query("SELECT r FROM Restaurant r JOIN FETCH r.coordinates")
    List<Restaurant> findAllWithCoordinates();

    List<Restaurant> findByOwner_Id(Long ownerId);  // 점주 ID로 식당 조회

    Optional<Restaurant> findByOwner(Member owner);

}
