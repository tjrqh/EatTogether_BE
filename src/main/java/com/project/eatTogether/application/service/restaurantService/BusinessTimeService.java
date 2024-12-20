package com.project.eatTogether.application.service.restaurantService;

import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantBusinessTimeRepository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BusinessTimeService {
  private final RestaurantBusinessTimeRepository restaurantBusinessTimeRepository;

  public List<String> getRestaurantBusinessTimeList(Long id) {
    return restaurantBusinessTimeRepository.findByRestaurantRsTime(id);
  }

  public void updatedRestaurantBusinessTime(Long id , String rsTime) {
      Optional<RsRestaurant> findRestaurant = restaurantBusinessTimeRepository.findById(id);
      RsRestaurant rsRestaurant = findRestaurant.orElseThrow(() ->
          new NoSuchElementException("Search Declare Not Found : " + id));
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
}
