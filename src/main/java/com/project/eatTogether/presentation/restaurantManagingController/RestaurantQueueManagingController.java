package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.application.dto.restaurantDto.QueueReadResponse;
import com.project.eatTogether.application.service.RestaurantService.QueueManagingService;
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
@RequestMapping("/owner/queue")
@RequiredArgsConstructor
public class RestaurantQueueManagingController {

  private final QueueManagingService queueManagingService;

  @GetMapping("/")
  public List<QueueReadResponse> restaurantQueue(@RequestParam String state) {
    Long id = 1L;   //Authorization 헤더 값 대신 사용 중
    return queueManagingService.restaurantQueueList(id,state);
  }

  @PutMapping("/")
  public ResponseEntity<String> restaurantUpdateQueue(@RequestParam String state){
    Long id = 1L;   //Authorization 헤더 값 대신 사용 중
    queueManagingService.restaurantUpdateQueue(id,state);
    return ResponseEntity.status(HttpStatus.OK).body("Success");
  }

}
