package com.project.eatTogether.presentation.controller.restaurantManagingController;

import com.project.eatTogether.application.dto.restaurantDto.RestaurantMenuReadResponse;
import com.project.eatTogether.application.dto.restaurantDto.RestaurantMenuUpdateRequest;
import com.project.eatTogether.application.service.MenuService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class RestaurantMenuManagingController {
  private final MenuService menuService;

  @GetMapping("")
  public List<RestaurantMenuReadResponse> getMenusByRestaurantId() {
    Long rsId = 1L;
    return menuService.getOwnerMenuByRsId(rsId);
  }

  @PutMapping("/update")
  public ResponseEntity<HttpStatus> updateMenu(@RequestBody RestaurantMenuUpdateRequest restaurantMenuUpdateRequest) {
    return menuService.getMenuUpdateRequest(restaurantMenuUpdateRequest);
  }

  @PostMapping("/create")
  public ResponseEntity<HttpStatus> createMenu (@RequestBody RestaurantMenuUpdateRequest restaurantMenuUpdateRequest) {
    Long rsId = 1L;
    return menuService.menuCreateRequest(restaurantMenuUpdateRequest,rsId);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteMenu (@PathVariable Long id) {
    return menuService.menuDeleteRequest(id);

  }

}
