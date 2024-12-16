package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.application.service.restaurantService.BusinessTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/hours")
@RequiredArgsConstructor
public class RestaurantBusinessTimeController {
  private final BusinessTimeService businessTimeService;


  @GetMapping("/")
  public List<String> getRestaurantBusinessTime(){
    Long rsId = 1L;
    return businessTimeService.getRestaurantBusinessTimeList(rsId);
  }

  @PutMapping("/")
  public ResponseEntity<String> updateRestaurantBusinessTime(@RequestBody String rsTime){
    Long rsId = 1L;
    businessTimeService.updatedRestaurantBusinessTime(rsId, rsTime);
    return ResponseEntity.status(HttpStatus.OK).body("Success");
  }
}
