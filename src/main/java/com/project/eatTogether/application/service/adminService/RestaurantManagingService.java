//package com.project.eatTogether.application.service.adminService;
//
//import com.project.eatTogether.application.dto.adminDto.RestaurantUnregisteredReadResponse;
//import com.project.eatTogether.domain.entity.RsRestaurant;
//import com.project.eatTogether.infrastructure.adminInfra.RestaurantManagingRepository;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class RestaurantManagingService {
//
//  private final RestaurantManagingRepository restaurantManagingRepository;
//
//  //미등록 레스토랑 등록 관련 페이지 Service
//  public List<RestaurantUnregisteredReadResponse> restaurantUnregisteredList(
//      String rsState, int page, int size) {
//    try {
//      Pageable pageable = PageRequest.of(page, size); // 페이지와 크기 설정
//      Page<RsRestaurant> restaurantPage = restaurantManagingRepository.findByRsState(rsState,
//          pageable);
//      return restaurantPage
//          .stream()
//          .map(restaurant -> RestaurantUnregisteredReadResponse
//              .builder()
//              .id(restaurant.getRsId())
//              .name(restaurant.getRsName())
//              .businessNumber(restaurant.getRsDocument().getRsDocumentBusinessId())
//              .address(restaurant.getRsCoordinates().getRestaurantAddr())
//              .phone(restaurant.getRsPhone())
//              // .email()
//              .hours(restaurant.getRsTime())
//              .menu(restaurant.getRsCuisineCategories().stream().toList().get(0).getRsCuisineCategoryName())
//              .additionalInfo(restaurant.getRsInfo())
//              .build())
//          .collect(Collectors.toList());
//    } catch (Exception e) {
//      log.error("checkReviewDeclare error : ", e);
//      throw new RuntimeException(
//          "Unexpected error occurred while processing review declare states : ", e);
//    }
//  }
//
//  // 식당 승인 반려 상태 변경
//  public void updateRestaurantState(Long id, String state) {
//    Optional<RsRestaurant> rs = restaurantManagingRepository.findById(id);
//
//    RsRestaurant rsRestaurant = rs.orElseThrow(
//        () -> new NoSuchElementException("Search Not Found : " + id));
//    rsRestaurant.setRsState(state);
//    restaurantManagingRepository.save(rsRestaurant);
//  }
//
//  public List<RestaurantUnregisteredReadResponse> getRestaurantByRsName(String rsName) {
//    try {
//          List<RsRestaurant> restaurantPage = restaurantManagingRepository.findByRsNameContaining(rsName);
//
//          return restaurantPage
//              .stream()
//              .map(restaurant -> RestaurantUnregisteredReadResponse
//                  .builder()
//                  .id(restaurant.getRsId())
//                  .name(restaurant.getRsName())
//                  .businessNumber(restaurant.getRsDocument().getRsDocumentBusinessId())
//                  .address(restaurant.getRsCoordinates().getRestaurantAddr())
//                  .phone(restaurant.getRsPhone())
//                  // .email()
//                  .hours(restaurant.getRsTime())
//                  .menu(restaurant.getRsCuisineCategories().stream().toList().get(0).getRsCuisineCategoryName())
//                  .additionalInfo(restaurant.getRsInfo())
//                  .build())
//              .collect(Collectors.toList());
//        } catch (Exception e) {
//          log.error("checkReviewDeclare error : ", e);
//          throw new RuntimeException(
//              "Unexpected error occurred while processing review declare states : ", e);
//        }
//  }
//
//}
