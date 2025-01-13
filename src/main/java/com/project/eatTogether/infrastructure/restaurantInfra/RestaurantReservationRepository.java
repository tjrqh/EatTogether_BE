package com.project.eatTogether.infrastructure.restaurantInfra;

import com.project.eatTogether.domain.entity.RsReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantReservationRepository extends JpaRepository<RsReservation, Long> {

  @Query("select r from RsReservation r where r.rsRestaurant.rsId = :rsId")
  List<RsReservation> findByRsRestaurantRsId(Long rsId);

  @Query("SELECT r FROM RsReservation r WHERE r.rsRestaurant.rsId =:rsId AND r.rsReservationDate < CURRENT DATE")
  List<RsReservation> findByRsId(Long rsId);

  // 결제 관련 추가 쿼리
  @Query("SELECT r FROM RsReservation r WHERE r.payment.paymentId = :paymentId")
  RsReservation findByPaymentId(@Param("paymentId") Long paymentId);

  // 중복 예약 체크를 위한 쿼리
  @Query("SELECT COUNT(r) > 0 FROM RsReservation r " +
          "WHERE r.rsRestaurant.rsId = :rsId " +
          "AND r.rsReservationDate = :date " +
          "AND r.rsReservationTime = :time " +
          "AND r.rsReservationState != 'CANCELLED'")
  boolean existsByRestaurantAndDateTime(
          @Param("rsId") Long rsId,
          @Param("date") LocalDate date,
          @Param("time") LocalDateTime time
  );

  // 회원의 예약 내역 조회
  @Query("SELECT r FROM RsReservation r " +
          "WHERE r.member.id = :memberId " +
          "ORDER BY r.rsReservationDate DESC")
  List<RsReservation> findByMemberIdOrderByDateDesc(@Param("memberId") Long memberId);


}
