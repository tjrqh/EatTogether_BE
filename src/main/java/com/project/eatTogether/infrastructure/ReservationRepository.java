package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.RsReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<RsReservation, Long> {

//    List<Reservation> findByRsRestaurant_RsId(Long rsId);
//
//    List<Reservation> findByMember_MemberId(Long memberId);
//
//    List<Reservation> findByRsRestaurant_RsIdAndRsReservationDeletedAtIsNull(Long rsId);
//
//    List<Reservation> findByMember_MemberIdAndRsReservationDeletedAtIsNull(Long memberId);
}