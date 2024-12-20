package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.application.dto.restaurantDto.ReservationReadResponse;
import com.project.eatTogether.domain.entity.RsReservation;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantReservationRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationManagingService {
  private final RestaurantReservationRepository restaurantReservationRepository;

  public List<ReservationReadResponse> reservationList(Long id){
    try {
          return restaurantReservationRepository.findByRsRestaurantRsId(id)
              .stream()
              .map(reservation -> ReservationReadResponse
                  .builder().rsReservationId(reservation.getRsReservationId())
                  .reservationPartySize(reservation.getRsReservationPartySize())
                  .rsReservationTime(reservation.getRsReservationTime())
                  .rsReservationRequest(reservation.getRsReservationRequest())
                  .rsReservationDate(reservation.getRsReservationDate())
                  .rsReservationState(reservation.getRsReservationState())
                  .build())
              .collect(Collectors.toList());
        } catch (Exception e) {
          log.error("restaurantQueue error : ", e);
          throw new RuntimeException(
              "Unexpected error occurred while processing restaurantQueue : ", e);
        }
  }

  public void updateReservationState(Long id, String state) {
    Optional<RsReservation> findReservation = restaurantReservationRepository.findById(id);

        RsReservation rsReservation = findReservation.orElseThrow(() ->
            new NoSuchElementException("Search Declare Not Found : " + id));
        try {
          rsReservation.setRsReservationState(state);
          restaurantReservationRepository.save(rsReservation);

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

}
