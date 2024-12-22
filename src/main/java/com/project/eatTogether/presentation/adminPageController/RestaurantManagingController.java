package com.project.eatTogether.presentation.adminPageController;


import com.project.eatTogether.application.dto.adminDto.RestaurantUnregisteredReadResponse;
import com.project.eatTogether.application.dto.adminDto.ReviewDeclareReadResponse;
import com.project.eatTogether.application.service.adminService.RestaurantManagingService;
import com.project.eatTogether.application.service.adminService.ReviewDeclareService;
import com.project.eatTogether.infrastructure.adminInfra.RestaurantManagingRepository;
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
@RequestMapping("/admin")
@RequiredArgsConstructor
public class RestaurantManagingController {

  private final RestaurantManagingService restaurantManagingService;
  private final RestaurantManagingRepository restaurantManagingRepository;
  private final ReviewDeclareService reviewDeclareService;

  @GetMapping("/restaurant/unregistered")  // 미 등록 식당 승인/반려 페이지
  public List<RestaurantUnregisteredReadResponse> restaurantUnregisteredList(@RequestParam String rsState, int page, int size) {

    return restaurantManagingService.restaurantUnregisteredList(rsState,page,size);
  }

  @PutMapping("/restaurant/unregistered/")
  public ResponseEntity<String> restaurantUnregisteredListUpdate(@RequestParam Long id, @RequestParam String rsState) {

    restaurantManagingService.updateRestaurantState(id ,rsState);
    return ResponseEntity.status(HttpStatus.OK).body("Complete Save");
  }

}
