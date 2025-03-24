package com.project.eatTogether.application.service.differed;

import com.project.eatTogether.application.dto.RsCuisineCategoriesDTO;
import com.project.eatTogether.domain.entity.Bookmark;
import com.project.eatTogether.domain.entity.RsCoordinates;
import com.project.eatTogether.domain.entity.RsCuisineCategories;
import com.project.eatTogether.domain.entity.RsRestaurant;
import com.project.eatTogether.domain.enums.CuisineType;
import com.project.eatTogether.infrastructure.BookmarkRepository;
import com.project.eatTogether.infrastructure.RsCuisineCategoriesRepository;
import com.project.eatTogether.infrastructure.differed.CuisineCategoriesRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CuisineCategoryService {

  private final CuisineCategoriesRepository cuisineCategoriesRepository;
  private final RsCuisineCategoriesRepository rsCuisineCategoriesRepository;
  private final BookmarkRepository bookmarkRepository;
  private Logger logger;


// CategoryName을 한글로 받아서, 이를 Enum으로 매핑
 public CuisineType getCuisineTypeFromDisplayName(String displayName) {
     try {
         return CuisineType.fromDisplayName(displayName);  // '한식' -> CuisineType.KOREAN
     } catch (IllegalArgumentException e) {
         // 유효하지 않은 카테고리 값에 대해 처리
         throw new IllegalArgumentException("유효하지 않은 카테고리: " + displayName);
     }
 }

 // 서비스에서 사용
 public List<RsCuisineCategoriesDTO> getCuisineCategoryByName(String categoryName, int page, int size, Long id) {
     // categoryName을 한글로 받은 후 이를 Enum으로 변환
     CuisineType cuisineType = getCuisineTypeFromDisplayName(categoryName);

     Pageable pageable = PageRequest.of(page, size);

     // 레포지토리에서 Enum 이름을 사용하여 데이터를 조회
     Page<RsCuisineCategories> entitiesPage = rsCuisineCategoriesRepository.findByRsCuisineCategoryName(
         cuisineType, pageable);  // Enum 이름인 KOREAN 등 사용

     return entitiesPage
         .stream()
         .map(cuisine -> {
             RsRestaurant restaurant = (RsRestaurant) cuisine.getRsRestaurants();
             RsCoordinates coordinates = restaurant.getRsCoordinates();

             return RsCuisineCategoriesDTO.builder()
                 .restaurantAddr(coordinates != null ? coordinates.getRestaurantAddr() : "주소 없음")
                 .rsCuisineCategoryName(cuisine.getType().getDisplayName())  // 한글 값 표시
                 .rsId(restaurant.getRsId())
                 .rsName(restaurant.getRsName())
                 .rsAvgRate(restaurant.getRsAvgRate())
                 .rsInfo(restaurant.getRsInfo())
                 .restaurantLat(coordinates != null ? coordinates.getRestaurantLat() : 0.0f)
                 .restaurantLong(coordinates != null ? coordinates.getRestaurantLong() : 0.0f)
                 .rsBookmarkCount(restaurant.getRsBookmarkCount())
                 .rsReviewCount(restaurant.getRsReviewCount())
                 .rsQueueEnabled(restaurant.isRsQueueEnabled())
                 .isPrepaid(restaurant.isPrepaid())
                 //.waitingTeams(restaurant.getRsReservationCount())
                 .build();
         })
         .collect(Collectors.toList());
 }
}