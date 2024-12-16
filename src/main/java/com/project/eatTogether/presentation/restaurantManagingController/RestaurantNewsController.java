package com.project.eatTogether.presentation.restaurantManagingController;

import com.project.eatTogether.domain.RsNews;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner/news")
@RequiredArgsConstructor
public class RestaurantNewsController {


  @GetMapping("")
  public RsNews getRestaurantNews() {
    Long id  = 1L;
    return null;
  }

}
