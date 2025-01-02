package com.project.eatTogether.infrastructure.differed;

import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.domain.enums.MemberRole;
import com.project.eatTogether.domain.enums.MemberStatus;
import com.project.eatTogether.domain.enums.OwnerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 기본 조회
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    // 점주 관련 조회
    boolean existsByBusinessRegistrationNumber(String businessRegistrationNumber);
    List<Member> findByRole(MemberRole role);
    List<Member> findByRoleAndMemberStatus(MemberRole role, MemberStatus status);

//    // 승인 대기 중인 점주 목록 조회
//    @Query("SELECT m FROM Member m WHERE m.role = :role AND m.OwnerStatus = :Ownerstatus")
//    List<Member> findPendingOwners(
//            @Param("role") MemberRole role,
//            @Param("status") MemberStatus status
//    );

    // 특정 점주가 보유한 식당 수 조회
    @Query("SELECT COUNT(r) FROM Member m JOIN m.restaurants r WHERE m.id = :id")
    long countRestaurantsById(@Param("id") Long id);

    // 식당을 보유한 점주 목록 조회
    @Query("SELECT DISTINCT m FROM Member m JOIN m.restaurants r WHERE m.role = :role")
    List<Member> findOwnersWithRestaurants(@Param("role") MemberRole role);

    // status 관련 메서드 수정
    List<Member> findByRoleAndOwnerStatus(MemberRole role, OwnerStatus ownerStatus);
}