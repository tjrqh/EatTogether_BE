//package com.project.eatTogether.application.service.restaurantService;
//
//import com.project.eatTogether.application.dto.restaurantDto.QueueReadResponse;
//import com.project.eatTogether.domain.entity.Queue;
//import com.project.eatTogether.infrastructure.restaurantInfra.RestaurantQueueRepository;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class QueueManagingService {
//
//  private final RestaurantQueueRepository restaurantQueueRepository;
//
//  public List<QueueReadResponse> restaurantQueueList(Long id, String state) {
//    List<Queue> result;
//    try {
//      if (Objects.equals(state, "waiting")) {
//        result = restaurantQueueRepository.waitingFindByRsRestaurantRsId(id);
//      } else {
//        result = restaurantQueueRepository.notWaitingFindByRsRestaurantRsId(id);
//      }
//      return result.stream()
//              .map(queue -> {
//                QueueReadResponse.QueueReadResponseBuilder builder = QueueReadResponse.builder()
//                        .queueId(queue.getQueueId())
//                        .queueNumber(queue.getQueueNumber())
//                        .queueDate(queue.getQueueDate())
//                        .queueTime(queue.getQueueTime())
//                        .queueState(queue.getQueueState())
//                        .queueOrder(queue.getQueueOrder() != null ? queue.getQueueOrder().getQueueOrderId() : null)
//                        .queueOrderRequestMemo(queue.getQueueOrder() != null ? queue.getQueueOrder().getQueueOrderRequestMemo() : null)
//                        .userName(queue.getUser().getUserName())
//                        .phone(queue.getUser().getUserPhone());
//                if (!Objects.equals(state, "waiting")) {
//                  builder.createdAt(queue.getCreatedAt().toString())    // queueCreatedAt -> createdAt
//                          .modifiedAt(queue.getModifiedAt().toString()); // queueUpdatedAt -> modifiedAt
//                }
//                return builder.build();
//              }).collect(Collectors.toList());
//    } catch (Exception e) {
//      log.error("restaurantQueue error : ", e);
//      throw new RuntimeException(
//              "Unexpected error occurred while processing restaurantQueue : ", e);
//    }
//  }
//
//  public void restaurantUpdateQueue(Long id, String state) {
//    Optional<Queue> findQueue = restaurantQueueRepository.findById(id);
//
//    Queue queue = findQueue.orElseThrow(() ->
//            new NoSuchElementException("Search Declare Not Found : " + id));
//    try {
//      queue.setQueueState(state);
//      restaurantQueueRepository.save(queue);
//    } catch (IllegalArgumentException e) {
//      log.error("Invalid state value: {}", e.getMessage());
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid state value", e);
//    } catch (DataAccessException e) {
//      log.error("Database error: {}", e.getMessage());
//      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", e);
//    } catch (Exception e) {
//      log.error("Unexpected error: {}", e.getMessage());
//      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
//              "Unexpected error occurred", e);
//    }
//  }
//
//}
