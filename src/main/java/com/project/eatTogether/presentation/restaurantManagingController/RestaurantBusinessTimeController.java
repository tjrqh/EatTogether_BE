package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.application.dto.restaurantDto.TemporaryCreateRequest;
import com.project.eatTogether.application.dto.restaurantDto.TemporaryReadResponse;
import com.project.eatTogether.application.service.restaurantService.BusinessTimeService;
import com.project.eatTogether.domain.entity.TemporarySchedule;
import com.project.eatTogether.domain.enums.Temporary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PostMapping("/temporary")
  public ResponseEntity<HttpStatus> updateRestaurantBusinessTime(@RequestBody TemporaryCreateRequest temporaryCreateRequest){
    Long rsId = 1L;
    return businessTimeService.createTemporary(rsId, temporaryCreateRequest);
  }

  @GetMapping("/watch")
  public List<TemporaryReadResponse> getRestaurantBusinessTimeWatch(@RequestParam(name = "temporary") Temporary temporary){
    Long rsId = 1L;
    return businessTimeService.findTemporarySchedule(rsId, temporary);
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteRestaurantBusinessTime(@PathVariable Long id){
    return businessTimeService.deleteTemporarySchedule(id);
  }
}
