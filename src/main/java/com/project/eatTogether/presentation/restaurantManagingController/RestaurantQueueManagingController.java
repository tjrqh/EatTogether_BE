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
@RequestMapping("/store/queue")
@RequiredArgsConstructor
public class RestaurantQueueManagingController {

  private final QueueManagingService queueManagingService;

  @GetMapping("/{id}")
  public List<QueueReadResponse> restaurantQueue(@PathVariable Long id) {
    return queueManagingService.restaurantQueueList(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> restaurantUpdateQUeue(@PathVariable Long id, @RequestParam String state){
    queueManagingService.restaurantUpdateQueue(id,state);
    return ResponseEntity.status(HttpStatus.OK).body("Success");
  }

}
