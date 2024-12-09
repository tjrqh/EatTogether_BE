package com.project.eatTogether.presentation.adminPageController;


import com.project.eatTogether.application.dto.adminDto.RestaurantUnregisteredReadResponse;
import com.project.eatTogether.application.service.adminService.RestaurantManagingService;
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
@RequestMapping("/admin/restaurant")
@RequiredArgsConstructor
public class RestaurantManagingController {

  private final RestaurantManagingService restaurantManagingService;

  @GetMapping("/unregistered")
  public List<RestaurantUnregisteredReadResponse> restaurantUnregisteredList(@RequestParam String rsState, int page, int size) {

    return restaurantManagingService.restaurantUnregisteredList(rsState,page,size);
  }

  @PutMapping("/unregistered/{id}")
  public ResponseEntity<String> restaurantUnregisteredListUpdate(@PathVariable Long id,
      @RequestParam String rsState) {
    restaurantManagingService.updateRestaurantState(id ,rsState);
    return ResponseEntity.status(HttpStatus.OK).body("Complete Save");
  }


}
