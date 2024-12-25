package com.project.eatTogether.presentation.adminPageController;


import com.project.eatTogether.application.dto.adminDto.RestaurantUnregisteredReadResponse;
import com.project.eatTogether.application.service.adminService.RestaurantManagingService;
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
@RequestMapping("/admin/restaurant")
@RequiredArgsConstructor
public class RestaurantManagingController {

  private final RestaurantManagingService restaurantManagingService;

  @GetMapping("/unregistered")  // 미 등록 식당 승인/반려 페이지
  public List<RestaurantUnregisteredReadResponse> restaurantUnregisteredList(@RequestParam String rsState, int page, int size) {

    return restaurantManagingService.restaurantUnregisteredList(rsState,page,size);
  }

  @PutMapping("/unregistered/")
  public ResponseEntity<String> restaurantUnregisteredListUpdate(@RequestParam Long id, @RequestParam String rsState) {

    restaurantManagingService.updateRestaurantState(id ,rsState);
    return ResponseEntity.status(HttpStatus.OK).body("Complete Save");
  }

  @GetMapping("/search")
  public List<RestaurantUnregisteredReadResponse> restaurantSearch(@RequestParam String rsName) {
    return restaurantManagingService.getRestaurantByRsName(rsName);
  }

}
