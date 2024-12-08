package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.application.dto.restaurantDto.ReservationReadResponse;
import com.project.eatTogether.application.service.RestaurantService.ReservationManagingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store/reservation")
@RequiredArgsConstructor
public class RestaurantReservationManagingController {

  private final ReservationManagingService reservationManagingService;

  @GetMapping("/{rsId}")    //가게 Id값
  public List<ReservationReadResponse> findReservationById(@PathVariable Long rsId) {
    return reservationManagingService.reservationList(rsId);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateReservation (@PathVariable Long id, @RequestParam String state) {
    reservationManagingService.updateReservationState(id, state);
    return ResponseEntity.status(HttpStatus.OK).body("Success");
  }
}
