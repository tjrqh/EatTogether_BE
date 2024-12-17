package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.application.dto.restaurantDto.RsRestaurantNewsReadResponse;
import com.project.eatTogether.application.service.restaurantService.RestaurantNewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/owner/news")
@RequiredArgsConstructor
public class RestaurantNewsController {

  private final RestaurantNewsService restaurantNewsService;

  @GetMapping("")   // 식당 소식 전체 목록
  public List<RsRestaurantNewsReadResponse> getRestaurantNews() {
    Long id = 1L;
    return restaurantNewsService.rsRestaurantNewsReadByRestaurantId(id);
  }

  @GetMapping("/modify/{id}")
  public List<RsRestaurantNewsReadResponse> getModifyRestaurantNews(@PathVariable Long id) {
    return restaurantNewsService.rsRestaurantNewsModifyPageReadByRsNewsId(id);
  }

  @PostMapping("/create")
  public ResponseEntity<String> createRestaurantNews(@RequestBody String rsNewsContent) {
    Long id = 1L;
    return restaurantNewsService.rsRestaurantNewsCreate(id,rsNewsContent);
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateRestaurantNews(@RequestParam Long rsNewsId, String rsNewsContent, List<MultipartFile> rsNewsImages) {
    Long id = 1L;
    return restaurantNewsService.rsRestaurantNewsUpdate(id, rsNewsId,rsNewsContent,rsNewsImages);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteRestaurantNews(@RequestBody Long id) {
    return restaurantNewsService.rsRestaurantNewsDelete(id);
  }

}
