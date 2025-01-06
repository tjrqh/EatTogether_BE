package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.application.dto.differed.reservation.ReservationRequestDto;
import com.project.eatTogether.application.dto.restaurantDto.ReservationReadResponse;
import com.project.eatTogether.domain.entity.RsReservation;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.differed.Member;
import com.project.eatTogether.infrastructure.RsRestaurantRepository;
import com.project.eatTogether.infrastructure.differed.MemberRepository;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationManagingService {

  private final RestaurantReservationRepository restaurantReservationRepository;
  private final RsRestaurantRepository restaurantRepository;
  private final MemberRepository memberRepository;

  /** 예약생성 */
  public Long createReservation(ReservationRequestDto requestDto) {
    // 레스토랑과 회원 조회
    RsRestaurant restaurant = restaurantRepository.findById(requestDto.getRsId())
            .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

    Member member = memberRepository.findById(requestDto.getMemberId())
            .orElseThrow(() -> new EntityNotFoundException("Member not found"));

    // 예약 시간 생성
    LocalDateTime reservationTime = LocalDateTime.of(
            requestDto.getRsReservationDate(),
            LocalTime.parse(requestDto.getRsReservationTime(), DateTimeFormatter.ofPattern("HH:mm"))
    );

    // 예약 생성
    RsReservation reservation = RsReservation.createReservation(
            restaurant,
            member,
            requestDto.getGuestName(),
            requestDto.getRsReservationPartySize(),
            requestDto.getRsReservationDate(),
            reservationTime,
            requestDto.getRsReservationRequest(),
            requestDto.getRsReservationRequest()
    );

    // 예약 저장
    RsReservation savedReservation = restaurantReservationRepository.save(reservation);

    return savedReservation.getRsReservationId();
  }


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
              .memberName(reservation.getMember().getName())
              .guest(reservation.getRsReservationPartySize())
              .time(formatReservationTime(reservation.getRsReservationTime()))
              .state(reservation.getRsReservationState())
              .date(reservation.getRsReservationDate())
              .deletedAt(reservation.getDeletedAt())
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

    rsReservation.delete(); // BaseTimeEntity의 delete() 메서드 사용
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
                  .memberName(reservation.getMember().getName())
                  .guest(reservation.getRsReservationPartySize())
                  .state(reservation.getRsReservationState())
                  .phone(reservation.getMember().getPhone())
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
