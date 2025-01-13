package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.entity.Payment;
import com.project.eatTogether.domain.entity.RsReservation;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.differed.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // 기본 CRUD 메서드들은 JpaRepository에서 제공됨
    Optional<Payment> findByReservation(RsReservation reservation);
    List<Payment> findByMember(Member member);
    List<Payment> findByRsRestaurant(RsRestaurant restaurant);
}
