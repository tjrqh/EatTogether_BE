package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.application.dto.restaurantDto.ReservationReadResponse;
import com.project.eatTogether.domain.entity.RsReservation;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantReservationRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationManagingService {

  private final RestaurantReservationRepository restaurantReservationRepository;

  private String formatReservationTime(LocalDateTime reservationTime) {
    if (reservationTime != null) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      return reservationTime.format(formatter);  // "HH:mm" 형식으로 포맷팅
    }
    return null;
  }

  public List<ReservationReadResponse> reservationList(Long id) {
    try {
      return restaurantReservationRepository.findByRsRestaurantRsId(id)
          .stream()
          .map(reservation -> ReservationReadResponse
              .builder().id(reservation.getRsReservationId())
              .userName(reservation.getUser().getUserName())
              .guest(reservation.getRsReservationPartySize())
              .time(formatReservationTime(reservation.getRsReservationTime()))
              .state(reservation.getRsReservationState())
              .date(reservation.getRsReservationDate())
              .deletedAt(reservation.getRsReservationDeletedAt())
              .build())
          .collect(Collectors.toList());
    } catch (Exception e) {
      log.error("restaurantQueue error : ", e);
      throw new RuntimeException(
          "Unexpected error occurred while processing restaurantQueue : ", e);
    }
  }

  public ResponseEntity<HttpStatus> updateReservationState(Long id, String state) {
    Optional<RsReservation> findReservation = restaurantReservationRepository.findById(id);

    RsReservation rsReservation = findReservation.orElseThrow(() ->
        new NoSuchElementException("Search ReservationId Not Found : " + id));
    try {
      rsReservation.setRsReservationState(state);
      restaurantReservationRepository.save(rsReservation);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid state value: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
    } catch (
        DataAccessException e) {
      System.out.println("Database error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
    } catch (Exception e) {
      System.out.println("Unexpected error: " + e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
          "Unexpected error occurred", e);
    }
  }

  public ResponseEntity<HttpStatus> deleteReservation(Long id) {
    Optional<RsReservation> findReservation = restaurantReservationRepository.findById(id);

    RsReservation rsReservation = findReservation.orElseThrow(() ->
        new NoSuchElementException("Search ReservationId Not Found : " + id));

    rsReservation.setRsReservationDeletedAt(LocalDateTime.now());
    restaurantReservationRepository.save(rsReservation);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  //지난 예약 보기
  public List<ReservationReadResponse> GetReservationsLastList(Long id){
    try {
          return restaurantReservationRepository.findByRsId(id)
              .stream()
              .map(reservation -> ReservationReadResponse
                  .builder()
                  .id(reservation.getRsReservationId())
                  .date(reservation.getRsReservationDate())
                  .time(formatReservationTime(reservation.getRsReservationTime()))
                  .userName(reservation.getUser().getUserName())
                  .guest(reservation.getRsReservationPartySize())
                  .state(reservation.getRsReservationState())
                  .phone(reservation.getUser().getUserPhone())
                  .request(reservation.getRsReservationRequest())
                  .build())
              .collect(Collectors.toList());
        } catch (Exception e) {
          log.error("restaurantQueue error : ", e);
          throw new RuntimeException(
              "Unexpected error occurred while processing restaurantQueue : ", e);
        }
  }

}
