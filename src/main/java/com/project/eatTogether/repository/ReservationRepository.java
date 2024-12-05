package com.project.eatTogether.repository;

import com.project.eatTogether.domain.entity.RsReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<RsReservation, Long> {

}
