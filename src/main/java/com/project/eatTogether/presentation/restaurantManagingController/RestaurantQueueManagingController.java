package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.application.dto.restaurantDto.QueueReadResponse;
import com.project.eatTogether.application.service.restaurantService.QueueManagingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  public List<QueueReadResponse> restaurantQueue(@RequestParam  String state) {
    Long id = 1L;
    return queueManagingService.restaurantQueueList(id,state);
  }

  @PutMapping("/")
  public ResponseEntity<String> restaurantUpdateQueue(@RequestParam Long id , String state){
    //Authorization 값 생성된다면 추가해서 넣어야 함 -> 식당 id 값과 조회해서 일치하면 변경되도록 해야 함
    queueManagingService.restaurantUpdateQueue(id,state);
    return ResponseEntity.status(HttpStatus.OK).body("Success");
  }

}
