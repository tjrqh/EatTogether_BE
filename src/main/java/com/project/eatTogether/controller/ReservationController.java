package com.project.eatTogether.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping ("/api")
public class ReservationController {

    //전체 예약정보 조회
    @GetMapping("/reservations")
    public void GetReservations(){

    }

    //식당 예약정보 조회
    @GetMapping("/restaurants/{restaurantId}/reservations")
    public void GetRestaurantReservations() {
    }

    //특정회원 예약 조회
    @GetMapping("/users/{userId}/reservations")
    public void GetUserReservations() {
    }

    //예약생성
    @PostMapping("/reservations")
    public void CreateReservations() {
    }

    //예약수정
    @PatchMapping("/reservations/{reservationId}")
    public void UpdateReservation() {
    }

    //예약삭제
    @DeleteMapping("/reservations/{reservationId}")
    public void DeleteReservation() {
    }


}
