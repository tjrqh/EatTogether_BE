package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.application.dto.restaurantDto.TemporaryCreateRequest;
import com.project.eatTogether.application.dto.restaurantDto.TemporaryReadResponse;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.entity.TemporarySchedule;
import com.project.eatTogether.domain.enums.Temporary;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantBusinessTimeRepository;
import com.project.eatTogether.infrastructure.restaurantInfra.TemporaryScheduleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BusinessTimeService {

  private final RestaurantBusinessTimeRepository restaurantBusinessTimeRepository;
  private final TemporaryScheduleRepository temporaryScheduleRepository;

  public List<String> getRestaurantBusinessTimeList(Long id) {
    return restaurantBusinessTimeRepository.findByRestaurantRsTime(id);
  }

  public void updatedRestaurantBusinessTime(Long id, String rsTime) {
    RsRestaurant rsRestaurant = findRestaurant(id);
    try {
      rsRestaurant.setRsTime(rsTime);
      restaurantBusinessTimeRepository.save(rsRestaurant);
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

  public ResponseEntity<HttpStatus> createTemporary(Long id,
      TemporaryCreateRequest temporaryCreateRequest) {

    try {
      RsRestaurant rsRestaurant = findRestaurant(id);
      TemporarySchedule temporarySchedule = TemporarySchedule.toEntity(temporaryCreateRequest,
          rsRestaurant);

      temporaryScheduleRepository.save(temporarySchedule);
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

  public List<TemporaryReadResponse> findTemporarySchedule(Long id,Temporary temporary) {
    List<TemporarySchedule> temporarySchedules = temporaryScheduleRepository.findByRsRestaurantRsId(id, temporary);
    return temporarySchedules.stream()
        .map(t -> new TemporaryReadResponse(t.getId(), t.getTemporary(), t.getTemporaryHours(), t.getTemporaryDate(), t.getExplanation()))
        .collect(Collectors.toList());
  }

  public ResponseEntity<HttpStatus> deleteTemporarySchedule(Long id) {
    try {
      TemporarySchedule temporarySchedule = temporaryScheduleRepository.findById(id).orElseThrow();
      temporarySchedule.setDeletedAt(LocalDateTime.now());
      temporaryScheduleRepository.save(temporarySchedule);

      return ResponseEntity.ok(HttpStatus.OK);
    }catch (IllegalArgumentException e) {
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


  private RsRestaurant findRestaurant(Long id) {
    Optional<RsRestaurant> findRestaurant = restaurantBusinessTimeRepository.findById(id);
    return findRestaurant.orElseThrow(() ->
        new NoSuchElementException("Search Declare Not Found : " + id));
  }

}
