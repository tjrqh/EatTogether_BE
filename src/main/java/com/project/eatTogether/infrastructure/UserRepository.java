package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.User;
import com.project.eatTogether.domain.enums.OwnerStatus;
import com.project.eatTogether.domain.enums.UserRole;
import com.project.eatTogether.domain.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 기본 조회
    Optional<User> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNickname(String userNickname);

    // 점주 관련 조회
    boolean existsByRsDocumentBusinessId(String rsDocumentBusinessId);
    List<User> findByUserRole(UserRole userRole);
    List<User> findByUserRoleAndUserStatus(UserRole role, UserStatus status);

    // 승인 대기 중인 점주 목록 조회
    @Query("SELECT m FROM User m WHERE m.userRole = :role AND m.ownerStatus = 'PENDING'")
    List<User> findPendingOwners();

    // 특정 점주가 보유한 식당 수 조회
    @Query("SELECT COUNT(r) FROM User m JOIN m.restaurants r WHERE m.userId = :userId")
    long countRestaurantsById(@Param("id") Long id);

    // 식당을 보유한 점주 목록 조회
    @Query("SELECT DISTINCT m FROM User m JOIN m.restaurants r WHERE m.userRole = :userRole")
    List<User> findOwnersWithRestaurants(@Param("userRole") UserRole userRole);

    // status 관련 메서드 수정
    List<User> findByUserRoleAndOwnerStatus(UserRole userRole, OwnerStatus ownerStatus);

    @Query("SELECT DISTINCT m FROM User m " +
            "LEFT JOIN FETCH m.restaurants " +
            "WHERE m.userRole = :userRole AND m.ownerStatus = :status")
    List<User> findByRoleAndOwnerStatusWithRestaurants(
            @Param("userRole") UserRole userRole,
            @Param("status") OwnerStatus status
    );

    List<User> findByUserNickname(String userNickname);
}