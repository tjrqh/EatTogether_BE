package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.Bookmark;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    // 특정 사용자의 특정 식당 북마크 조회
    Optional<Bookmark> findByUserAndRsRestaurant(User user, RsRestaurant rsRestaurant);

    @Query("SELECT b FROM Bookmark b Where b.user.userId = :userId AND b.rsRestaurant.rsId = :id")
   Bookmark findByRsRestaurantRsIdAndUserUserId(Long id,Long userId);
}